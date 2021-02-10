/**
 * Created by satyam on 9/18/2015.
 */
var start = 0;
var limit =5;

function loadMoreMsgs(){

    var forumMainPnl = $('#ExampleLoadMoreAppendTarget');
    var noDataPnl = $('#nodataPnl');
    if(noDataPnl){
        noDataPnl.hide();
    }
    if(forumMainPnl){
        var config  =  {
            url : 'msg/loadMoreMsgs',
            data : {start : start, limit : 10},
            genErrToast : false,


            success : function(res){
                addNewMsgs(res, forumMainPnl);
            }
        };
        myAjax(config);
    }
};
function addNewMsgs(data,forumMainPnl){
    var forums = data;
    var i = -1;

    while(++i < forums.length){
        var each = forums[i];
        if(each){
            var str =          '' ;
            str+= '<section class="simple-compose-box mb-xlg panel-body msg-box"> ' +
                '       <div class="inner-msg-box">' +
                '        <div class="profile-pic ">' +
                '           <img src="getImageDocument?filePath='+each.image+'" alt="ORL Member"  onerror="this.src=\'resources/images/user.jpg\'"   > '+
                '       </div>' +

                '        <div class="message-body">' +
                '           <div> ' +
                '               <span class="name" title="'+each.createdBy+'" >' +
                '                   <a class="name-title" href="profile.html?userId='+ each.createdById +'&groupId='+GLOBAL_GRP_ID+'">'+each.createdBy+'</a>' +
                '               </span>' +
                '               <span> on <span class="timestamp"> <i class="em700"></i> ' + each.createdDateStr + ' </span ></span>' +
                '               <span class="options"> <i class="fa fa-arrow-down"></i> </span ></span>' +
                '           </div>'+
                '           <span class="role"> <i class="fa fa-user"></i> '+ each.createdUserRole+' </span >  '+

                '       <div class="message">'+
                '           <p>'+each.msgTitle+'</p>'+
                '       </div>'+
                '       </div>'+
            '           <div class="compose-box-footer" style="border-top:1px solid #d1d1d1">';



            var comments = each['threadList'];
            if(comments){
                var j = -1;
                var comment = null;
                while(++j < comments.length){
                    comment = comments[j];

                    str+=' <div class="eachcomment simple-compose-box clearfix"  >' +
                        '       <div class="profile-pic-small" title="'+comment.createdBy+'">' +
                    '                <img src="getImageDocument?filePath='+comment.image+'" alt="ORLounge member"  onerror="this.src=\'resources/images/user.jpg\'"    />'+
                    '           </div>' +

                    '           <div  class="message-body">' +
                    '               <div> <span class="name-title-small" title="'+comment.createdBy+'" >' +
                        '                   <a class="custom-blue" href="profile.html?userId='+ comment.createById +'&groupId='+GLOBAL_GRP_ID+'">'+comment.createdBy+'</a></span> '+
                    '                   <span class="role"> <i class="fa fa-user"></i> '+ comment.createdUserRole+' </span >  '+
                    '                   <span class="timestamp"><i class="fa fa-globe"></i> ' +comment.createdDateStr+'</span >';
                    if(typeof role !== 'undefined' && role && (role ==='orm' || role === 'admin')){
                        str+= ' &nbsp; &nbsp;  <a  class="custom-blue underline" href="deleteMsgComment?commentId='+comment.commentId+'"  >Delete</a> ';
                    }

                    str+= '               <div class="message">'+
                                        comment.comment+
                    '               </div></div>'+
                    '           </div>' +

                    '       </div>';

            }

            if(comments.length === 0){
                // str+='<div class="eachcomment simple-compose-box">No Comments yet.</div>';

            }
            }

            if(role && role !== 'guest'){
                str+= '   </div><div class="simple-compose-box comment-box clearfix">'+
                    '       <div class="profile-pic-small">' +
                    '                <img src="getImageDocument?filePath='+myImage+'" alt=""/>'+
                    '           </div>' +
                    '   <div class="message-body">'+
                    '       <section class="simple-compose-box" style=""> '+
                    '           <form method="POST" id="comment_forum_'+each.msgId+'" action="postMsgComment"> '+
                    '               <input type="hidden" name="msgId" value="'+each.msgId+'"> '+
                    '               <textarea class="text-area multiline-text light-border" rows="1" name="text" data-plugin-textarea-autosize placeholder="Write a Comment"></textarea> '+
                    '            </form> '+
                    '           <div class="compose-box-footer"> '+
                    '               <ul class="compose-btn"> '+
                    '                   <li> '+
                    '                       <a class="btn btn-primary btn-xs" onclick="saveComment('+each.msgId+')">Comment</a> '+
                    '                       </li> '+
                    '                   </ul> '+
                    '               </div> '+
                    '           </section> '+
                    '</div></div>'+

                    '     </div>'+
                    '</section>';


            }


            forumMainPnl.append(str);

        }


    }
    start+= limit;

}

function dataSubmit123() {
	 var x = $('#descriptionB').parent().find('.note-editable').html();
	 alert("content is::"+x);
       $.ajax({
           type: "POST",
           contentType: "application/text",
           url: "saveEditorContent",
           data : {content123 : x, limit : 10},
           dataType: 'text',
           timeout: 600000,
           success: function (data) {
               $('#ins-prefId').val(data.id);
               //$('#ins-prefId').prop('disabled',true);
           },
           error: function (e) {
               $("#btn-save").prop("disabled", false);
           }
       });

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
    loadMoreMsgs();
}, 100);
;
