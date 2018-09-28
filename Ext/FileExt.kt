package github.nixo.com.github.Ext

import android.util.Log
import github.nixo.com.common.no
import github.nixo.com.common.otherwise
import github.nixo.com.common.yes
import java.io.File


private const val TAG = "FileExt"


fun File.ensureDir():Boolean{
    try{
        // 如果这个路径不是文件夹，我们就去看看是不是File
        isDirectory.no{
            //如果是File文件，删掉
            isFile.yes {
                delete()
            }
        }.otherwise {
            //然后我们重新创建文件夹
            return mkdir()
        }
    } catch (e:Exception){
        Log.w(TAG , e.message)
    }
    //如果都不是 返回false
    return false
}