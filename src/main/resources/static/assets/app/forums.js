/**
 * Created by satyam on 9/18/2015.
 */
var start = 0;
var limit =5;
function loadMoreForums(){

    var forumMainPnl = $('#ExampleLoadMoreAppendTarget');
    var noDataPnl = $('#nodataPnl');
    if(noDataPnl){
        noDataPnl.hide();
    }
    if(forumMainPnl){
        var config  =  {
            url : 'forum/loadMoreForums?role='+showFor,
            data : {start : start, limit : 5},
            genErrToast : false,


        success : function(res){
                addNewForums(res, forumMainPnl);
            }
        };
        myAjax(config);
    }
};
function loadMoreMsgs(){

    var forumMainPnl = $('#ExampleLoadMoreAppendTarget');
    var noDataPnl = $('#nodataPnl');
    if(noDataPnl){
        noDataPnl.hide();
    }
    if(forumMainPnl){
        var config  =  {
            url : 'msg/loadMoreMsgs',
            data : {start : start, limit : 5},
            genErrToast : false,


            success : function(res){
                addNewForums(res, forumMainPnl);
            }
        };
        myAjax(config);
    }
};

function addNewForums(data,forumMainPnl){
    var forums = data;
    var i = -1;

    while(++i < forums.length){
        var each = forums[i];
        if(each){
            var str =          '' ;
            str+= '<section class="simple-compose-box mb-xlg panel-body"> '+
                '        <div class="profile-pic">' +
                '           <img src="getImageDocument?filePath='+each.image+'" alt="" />'+
                '       </div>' +

                '        <div class="message-body">' +
                '           <div> <span class="name">'+each.createdBy+'</span>'+
                '           <span class="role"> <i class="fa fa-user"></i> '+ each.createdUserRole+' </span >  '+
                '           <div class="timestamp"> <i class="fa fa-globe"></i> ' +each.createdDateStr+'</div >'+
                '           </div>'+

                '       <div class="message">'+
                '           <p class="bold">'+each.forumTitle+'</p>'+
                '       </div>'+
                '       </div>' +
                '           <div class="compose-box-footer" style="border-top:1px solid #d1d1d1">';



            var comments = each['threadList'];
            if(comments){
                var j = -1;
                var comment = null;
                while(++j < comments.length){
                    comment = comments[j];

                    str+=' <div class="eachcomment simple-compose-box clearfix" style="border-bottom:'+ (j == comments.length -1 ? '0px' : '1px') +' solid  #d1d1d1;" >' +
                        '       <div class="profile-pic">' +
                        '                <img src="getImageDocument?filePath='+comment.image+'" alt="" />'+
                        '           </div>' +

                        '           <div class="message-body">' +
                        '               <div> <span class="name">'+comment.createdBy+'</span> &nbsp;&nbsp;'+
                        '                   <span class="role"> <i class="fa fa-user"></i> '+ comment.createdUserRole+' </span >  '+
                        '                   <span class="timestamp"><i class="fa fa-globe"></i> ' +comment.createdDateStr+'</span >';
                    if(typeof role !== 'undefined' && role && (role ==='orm' || role === 'admin')){
                        str+= ' &nbsp; &nbsp;  <a  style="font-color:darkblue;text-decoration:underline;" href="deleteComment?commentId='+comment.commentId+'"  >Delete</a> ';
                    }

                    str+= '                <div class="message">'+
                        comment.comment+
                        '              </div> </div>'+
                        '           </div>' +

                        '       </div>';

                }

                if(j == 0){
                    str+='<div class="eachcomment simple-compose-box">No Comments yet.</div>';

                }
            }

            if(role && role !== 'guest'){
                str+= '   </div>'+
                    '       <div class="simple-compose-box comment-box clearfix">' +
                    '       <div class="profile-pic">' +
                    '                <img src="getImageDocument?filePath='+myImage+'" alt="" />'+
                    '           </div>' +
                    '   <div class="message-body">'+
                    '       <section class="simple-compose-box" style=""> '+
                    '           <form method="POST" id="comment_forum_'+each.forumId+'" action="postForumComment"> '+
                    '               <input type="hidden" name="forumId" value="'+each.forumId+'"> '+
                    '               <textarea rows="1" name="text" data-plugin-textarea-autosize placeholder="Write a Comment"></textarea> '+
                    '            </form> '+
                    '           <div class="compose-box-footer"> '+
                    '               <ul class="compose-btn"> '+
                    '                   <li> '+
                    '                       <a class="btn btn-primary btn-xs" onclick="saveComment('+each.forumId+')">Comment</a> '+
                    '                       </li> '+
                    '                   </ul> '+
                    '               </div> '+
                    '           </section> '+
                    '</div></div>'+
                    '</section>';

            }



            forumMainPnl.append(str);

        }


    }
    start+= limit;

    if(i == 0 ){
        new PNotify({
            title: 'Info',
            text:   "Seems  All Caught Up Folks!!!.",
            type: 'info'
        })
    }
}

function savePost(){
    $('#postForum').submit();
}

function saveComment(id){
    $('#comment_forum_'+id).submit();
}
setTimeout(function(){
    var noDataPnl = $('#nodataPnl');
    if(noDataPnl){
        noDataPnl.hide();
    }
    loadMoreForums();
}, 100);
;
