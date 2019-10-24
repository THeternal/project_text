var host = "http://" + location.host + "/daiken/im/";

;
(function(RCS) {
	var getTemplates = function(callback) {
		var list = {
			button : host + 'templates/button.html',
			chat : host + 'templates/chat.html',
			closebefore : host + 'templates/closebefore.html',
			conversation : host + 'templates/conversation.html',
			endconversation : host + 'templates/endconversation.html',
			evaluate : host + 'templates/evaluate.html',
			imageView : host + 'templates/imageView.html',
			leaveword : host + 'templates/leaveword.html',
			main : host + 'templates/main.html',
			imMain : host + 'templates/imMain.html',
			message : host + 'templates/message.html',
			imMessage : host + 'templates/imMessage.html',
			messageTemplate : host + 'templates/messageTemplate.html',
			imMessageTemplate : host + 'templates/imMessageTemplate.html',
			userInfo : host + 'templates/userInfo.html',
		};
		var templates = {};
		for ( var key in list) {
			var url = list[key];
			var html = RCS.templateCache[url];
			if (html) {
				templates[key] = html;
			} else {
				var xhr = new XMLHttpRequest();
				xhr.open('get', url, false);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						templates[key] = xhr.responseText;
					}
				}
				xhr.send(null);
			}

		}
		return templates;
	}
	RCS.getTemplates = getTemplates;
})(RCS);