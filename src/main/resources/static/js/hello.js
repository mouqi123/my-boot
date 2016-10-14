/**
 * 
 */
$(document).ready(function() {
    $.ajax({
        url: "/test1"
    }).then(function(data) {
    	console.log(data);
       $('.greeting-id').append(data.userId);
       $('.greeting-content').append(data.userName);
    });
});