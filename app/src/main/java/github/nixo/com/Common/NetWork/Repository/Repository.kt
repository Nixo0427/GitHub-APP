package github.nixo.com.Common.NetWork.Repository

import android.os.Parcelable
import github.nixo.com.github.Common.Model.BasicUser
import github.nixo.com.github.anno.PoKo
import kotlinx.android.parcel.Parcelize

/**
 * {
"id": 145996419,
"node_id": "MDEwOlJlcG9zaXRvcnkxNDU5OTY0MTk=",
"name": "Android-KtExt-Utils",
"full_name": "Nixo0427/Android-KtExt-Utils",
"private": false,
"owner": {
"login": "Nixo0427",
"id": 32899769,
"node_id": "MDQ6VXNlcjMyODk5NzY5",
"avatar_url": "https://avatars3.githubusercontent.com/u/32899769?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/Nixo0427",
"html_url": "https://github.com/Nixo0427",
"followers_url": "https://api.github.com/users/Nixo0427/followers",
"following_url": "https://api.github.com/users/Nixo0427/following{/other_user}",
"gists_url": "https://api.github.com/users/Nixo0427/gists{/gist_id}",
"starred_url": "https://api.github.com/users/Nixo0427/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/Nixo0427/subscriptions",
"organizations_url": "https://api.github.com/users/Nixo0427/orgs",
"repos_url": "https://api.github.com/users/Nixo0427/repos",
"events_url": "https://api.github.com/users/Nixo0427/events{/privacy}",
"received_events_url": "https://api.github.com/users/Nixo0427/received_events",
"type": "User",
"site_admin": false
},
"html_url": "https://github.com/Nixo0427/Android-KtExt-Utils",
"description": null,
"fork": false,
"url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils",
"forks_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/forks",
"keys_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/keys{/key_id}",
"collaborators_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/collaborators{/collaborator}",
"teams_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/teams",
"hooks_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/hooks",
"issue_events_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/issues/events{/number}",
"events_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/events",
"assignees_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/assignees{/user}",
"branches_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/branches{/branch}",
"tags_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/tags",
"blobs_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/git/blobs{/sha}",
"git_tags_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/git/tags{/sha}",
"git_refs_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/git/refs{/sha}",
"trees_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/git/trees{/sha}",
"statuses_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/statuses/{sha}",
"languages_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/languages",
"stargazers_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/stargazers",
"contributors_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/contributors",
"subscribers_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/subscribers",
"subscription_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/subscription",
"commits_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/commits{/sha}",
"git_commits_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/git/commits{/sha}",
"comments_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/comments{/number}",
"issue_comment_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/issues/comments{/number}",
"contents_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/contents/{+path}",
"compare_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/compare/{base}...{head}",
"merges_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/merges",
"archive_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/{archive_format}{/ref}",
"downloads_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/downloads",
"issues_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/issues{/number}",
"pulls_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/pulls{/number}",
"milestones_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/milestones{/number}",
"notifications_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/notifications{?since,all,participating}",
"labels_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/labels{/name}",
"releases_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/releases{/id}",
"deployments_url": "https://api.github.com/repos/Nixo0427/Android-KtExt-Utils/deployments",
"created_at": "2018-08-24T13:41:02Z",
"updated_at": "2018-08-27T13:28:43Z",
"pushed_at": "2018-08-27T13:28:42Z",
"git_url": "git://github.com/Nixo0427/Android-KtExt-Utils.git",
"ssh_url": "git@github.com:Nixo0427/Android-KtExt-Utils.git",
"clone_url": "https://github.com/Nixo0427/Android-KtExt-Utils.git",
"svn_url": "https://github.com/Nixo0427/Android-KtExt-Utils",
"homepage": null,
"size": 4,
"stargazers_count": 0,
"watchers_count": 0,
"language": "Kotlin",
"has_issues": true,
"has_projects": true,
"has_downloads": true,
"has_wiki": true,
"has_pages": false,
"forks_count": 0,
"mirror_url": null,
"archived": false,
"open_issues_count": 0,
"license": null,
"forks": 0,
"open_issues": 0,
"watchers": 0,
"default_branch": "master"
},
 */
@PoKo
@Parcelize
data class Repository(var id: Int,
                      var name: String,
                      var full_name: String,
                      var owner: BasicUser,
                      var `private`: Boolean,
                      var html_url: String,
                      var description: String?,
                      var fork: Boolean,
                      var url: String,
                      var forks_url: String,
                      var keys_url: String,
                      var collaborators_url: String,
                      var teams_url: String,
                      var hooks_url: String,
                      var issue_events_url: String,
                      var events_url: String,
                      var assignees_url: String,
                      var branches_url: String,
                      var tags_url: String,
                      var blobs_url: String,
                      var git_tags_url: String,
                      var git_refs_url: String,
                      var trees_url: String,
                      var statuses_url: String,
                      var languages_url: String,
                      var stargazers_url: String,
                      var contributors_url: String,
                      var subscribers_url: String,
                      var subscription_url: String,
                      var commits_url: String,
                      var git_commits_url: String,
                      var comments_url: String,
                      var issue_comment_url: String,
                      var contents_url: String,
                      var compare_url: String,
                      var merges_url: String,
                      var archive_url: String,
                      var downloads_url: String,
                      var issues_url: String,
                      var pulls_url: String,
                      var milestones_url: String,
                      var notifications_url: String,
                      var labels_url: String,
                      var releases_url: String,
                      var deployments_url: String,
                      var created_at: String,
                      var updated_at: String,
                      var pushed_at: String,
                      var git_url: String,
                      var ssh_url: String,
                      var clone_url: String,
                      var svn_url: String,
                      var homepage: String?,
                      var size: Int,
                      var stargazers_count: Int,
                      var watchers_count: Int,
                      var language: String?,
                      var has_issues: Boolean,
                      var has_projects: Boolean,
                      var has_downloads: Boolean,
                      var has_wiki: Boolean,
                      var has_pages: Boolean,
                      var forks_count: Int,
                      var mirror_url: String?,
                      var open_issues_count: Int,
                      var forks: Int,
                      var open_issues: Int,
                      var watchers: Int,
                      var subscribers_count: Int = 0,
                      var default_branch: String,
                      var permissions: Permissions?) : Parcelable

@PoKo
@Parcelize
data class Permissions(var admin: Boolean,
                       var push: Boolean,
                       var pull: Boolean) : Parcelable