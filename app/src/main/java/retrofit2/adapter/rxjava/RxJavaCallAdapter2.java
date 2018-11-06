/*
 * Copyright (C) 2016 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;

import javax.annotation.Nullable;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.adapter.rxjava.BodyOnSubscribe;
import retrofit2.adapter.rxjava.CallEnqueueOnSubscribe;
import retrofit2.adapter.rxjava.CallExecuteOnSubscribe;
import retrofit2.adapter.rxjava.ResultOnSubscribe;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;

final class RxJavaCallAdapter2<R> implements CallAdapter<R, Object> {
  private final Type responseType;
  private final @Nullable Scheduler scheduler;
  private final @Nullable Scheduler observableOn;
  private final boolean isAsync;
  private final boolean isResult;
  private final boolean isBody;
  private final boolean isPaging;

  private final boolean isSingle;
  private final boolean isCompletable;

  RxJavaCallAdapter2(Type responseType, @Nullable Scheduler scheduler,@Nullable Scheduler observableOn, boolean isAsync,
                     boolean isResult, boolean isBody,boolean isPaging, boolean isSingle, boolean isCompletable) {
    this.responseType = responseType;
    this.scheduler = scheduler;
    this.observableOn = observableOn;
    this.isAsync = isAsync;
    this.isResult = isResult;
    this.isBody = isBody;
    this.isPaging = isPaging;
    this.isSingle = isSingle;
    this.isCompletable = isCompletable;
  }

  @Override public Type responseType() {
    return responseType;
  }

  @Override public Object adapt(Call<R> call) {
    OnSubscribe<Response<R>> callFunc = isAsync
        ? new CallEnqueueOnSubscribe<>(call)
        : new CallExecuteOnSubscribe<>(call);

    OnSubscribe<?> func;
    if (isResult) {
      func = new ResultOnSubscribe<>(callFunc);
    } else if (isBody) {
      func = new BodyOnSubscribe<>(callFunc);
    }else if (isPaging){
      // TODO: 2018/11/6
      func = new GitHubListOnSubscribe<>(callFunc);
    } else {
      func = callFunc;
    }
    Observable<?> observable = Observable.create(func);

    if (scheduler != null) {
      observable = observable.subscribeOn(scheduler);
    }
    if (observable != null) {
      observable = observable.observeOn(observableOn);
    }

    if (isSingle) {
      return observable.toSingle();
    }
    if (isCompletable) {
      return observable.toCompletable();
    }
    return observable;
  }
}
