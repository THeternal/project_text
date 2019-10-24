(function (RCS) {
	var utils = RCS.utils;
	var emoji = RCS.emoji;
	var render = utils.render;
	var conversation = {};
	conversation.lastSendTime = 0;
	conversation.messageContent = [];
	var voicePlay = null;
	var userInfoValue = {}; //保存收集用户信息的相关数据
	var templates = {};
	var $ = utils.$;
	var terminal;
	var supportNot = false; //页面是否支持notification

	//加载模板
	var getTemplates = function (callback) {
		templates = RCS.getTemplates();
		callback && callback();
	}

	//键盘回车发送
	var keySend = function (event) {
		if (event.keyCode == '13' && !event.shiftKey) {
			event.preventDefault()
			send();
		} else {
			inputChange();
		}
	}
	//发送
	var send = function () {
		var inputMsg = $(".rongcloud-text")[0];
		var message = inputMsg.value;
		if (message) {
			message = emoji.symbolToEmoji(message);
			sendMessage(new RongIMLib.TextMessage({
				content: message,
				extra: "附加信息",
				user:RCS.config.user
			}));
			inputMsg.value = '';
			inputMsg.focus();
		}
	}
	//每6秒执行一次正在输入消息发送
	var inputChange = function () {
		var timespan = new Date().getTime() - conversation.lastSendTime;
		if (timespan > 1000 * 6) {
			conversation.lastSendTime += timespan;
			sendTyping();
		}
	}
	//正在输入中
	var sendTyping = function () {
		if (conversation.targetType == RongIMLib.ConversationType.CUSTOMER_SERVICE) {
			var msg = new RongIMLib.TypingStatusMessage({
				typingContentType: 'RC:TxtMsg',
				data: null,
				user:RCS.config.user
			});
			var callback = function () {};
			sendMessage(msg, callback);
		}
	}
	//显示表情
	var showemoji = function (event) {
		event.stopPropagation();
		var emojiContent = $('.rongcloud-expressionWrap')[0];
		if (emojiContent.style.display == 'none') {
			utils.show(emojiContent);
		} else {
			utils.hide(emojiContent);
		}
	}
	//表情点击
	var chooseEmoji = function (event) {
		event.stopPropagation();
		var emojiContent = $('.rongcloud-expressionWrap')[0];
		var thisTarget = event.target || event.srcElement;
		var textArea = $('.rongcloud-text')[0];
		var emojiName = thisTarget.getAttribute('name');
		if (emojiName) {
			textArea.value += emojiName;
			utils.hide(emojiContent);
			if (terminal == 'pc') {
				textArea.focus();
				range = document.createRange();
				range.selectNodeContents(textArea);
				range.collapse(true);
				range.setEnd(textArea, textArea.childNodes.length);
				range.setStart(textArea, textArea.childNodes.length);
				sel = window.getSelection();
				sel.removeAllRanges();
				sel.addRange(range);
			}
		}
	}

	function textMessageFormat(content) {
		if (content.length === 0) {
			return '';
		}

		content = utils.encodeHtmlStr(content);

		content = utils.replaceUri(content, function (uri, protocol) {
			var link = uri;
			if (!protocol) {
				link = 'http://' + uri;
			}
			return '<a class="rong-link-site" target="_blank" href="' + link + '">' + uri + '</a>';
		});

		content = utils.replaceEmail(content, function (email) {
			return '<a class="rong-link-email" href="mailto:' + email + '">' + email + '<a>';
		});

		return emoji.emojiToHTML(content, 18);
	}

	//发送消息
	var sendMessage = function (msg, callback) {
		var targetId = conversation.id; // 目标 Id
		RongIMClient.getInstance().sendMessage(conversation.targetType, targetId, msg, {
			// 发送消息成功
			onSuccess: function (message) {
				console.log(message);
				//message 为发送的消息对象并且包含服务器返回的消息唯一Id和发送消息时间戳
				console.log("Send successfully");
				callback && callback();
				RCS.config.sendCallback && RCS.config.sendCallback(message);
				if (!callback) {
					updateConversationList();
					updateMessage(message);
				}
			},
			onError: function (errorCode, message) {
				var info = '';
				switch (errorCode) {
					case RongIMLib.ErrorCode.TIMEOUT:
						info = '超时';
						break;
					case RongIMLib.ErrorCode.UNKNOWN_ERROR:
						info = '未知错误';
						break;
					case RongIMLib.ErrorCode.REJECTED_BY_BLACKLIST:
						info = '在黑名单中，无法向对方发送消息';
						break;
					case RongIMLib.ErrorCode.NOT_IN_DISCUSSION:
						info = '不在讨论组中';
						break;
					case RongIMLib.ErrorCode.NOT_IN_GROUP:
						info = '不在群组中';
						break;
					case RongIMLib.ErrorCode.NOT_IN_CHATROOM:
						info = '不在聊天室中';
						break;
					default:
						info = x;
						break;
				}
				console.log('发送失败:' + info);
			}
		});
	}

	//显示新消息
	var updateMessage = function (message) {
		if (message.messageType == 'ReadReceiptMessage') {
			return; //ReadReceiptMessage的messageType
		}
		conversation.messageContent.push(message);
		var newMessage = modifyMessage(utils.cloneObj(message));
		if (message.messageDirection != 1 && supportNot) {
			pushMessage(newMessage);
		}
		var messageList = $(".rcs-message-box")[0];
		if (!messageList) {
			return;
		}
		// if (newMessage.sentTime - conversation.lastSendTime >= 60000) { //超过1分钟
		// 	var messageTime = {};
		// 	messageTime.content = {};
		// 	messageTime.messageType = 'TimeMessage';
		// 	messageTime.sentTime = utils.getTime(newMessage.sentTime);
		// 	messageList.innerHTML += render(templates.imMessageTemplate, messageTime);
		// 	conversation.lastSendTime = newMessage.sentTime;
		// }
		messageList.innerHTML += render(templates.imMessageTemplate, newMessage);
		messageList.scrollTop = messageList.scrollHeight;
	}

	//web push message
	var pushMessage = function (msg) {
		if (terminal == 'pc') {
			var title = '消息提醒';
			var options = {
				body: "您有一条新消息，请及时回复",
				icon: (msg.content.user && msg.content.user.icon) ? msg.content.user.icon : "./images/kefu.png",
			};
			var notification = new Notification(title, options);

			notification.onclick = function (event) {
				window.focus();
				notification.close();
			}
			notification.onshow = function () {
				setTimeout(function () {
					notification.close();
				}, 5000);
			};
		}
	}

	//图片新消息图片加载完毕滚动到最下面
	var scrollBottom = function () {
		var messageList = $(".rcs-message-box")[0];
		messageList.scrollTop = messageList.scrollHeight;
	}
	//加载历史消息
	var loadHisMessages = function () {
		var callbacks = function (list, hasMsg) {
			var messageBox = $(".rcs-message-box")[0];
			var messageList = {};
			messageList.hasMore = hasMsg;
			messageList.list = modificateMessage(conversation.messageContent);
			var oldHeight = messageBox.scrollHeight;
			messageBox.innerHTML = render(templates.imMessage, messageList);
			var newHeight = messageBox.scrollHeight;
			messageBox.scrollTop = newHeight - oldHeight;
		}
		getHisMessage(conversation.id, null, 20, callbacks);
	}

	var createIMConversation = function (config) {
		var data = {
			"showConversitionList": true
		}
		var callback = function (list) {
			var obj = {};
			obj.list = list;
			data.conversationList = render(templates.conversation, obj);
			$(".customer-service")[0].innerHTML = render(templates.imMain, data);
		}
		getConversationList(callback);
	}

	//开始会话
	var startConversation = function (event, name) {
		var thisTarget = event.target || event.srcElement;
		if (thisTarget.className.indexOf('rongcloud-sprite') != -1) {
			event.currentTarget.parentNode.removeChild(event.currentTarget);
			removeConversation(event.currentTarget.getAttribute("_cid"));
			if (conversation.id == event.currentTarget.getAttribute("_cid")) {
				$('.rcs-chat-wrapper')[0].innerHTML = '';
			}
			return;
		}
		if (conversation.id == event.currentTarget.getAttribute("_cid")) {
			return;
		}
		conversation.messageContent = [];
		conversation.id = event.currentTarget.getAttribute("_cid");
		conversation.mcount = event.currentTarget.getAttribute('_mcount');
		if (conversation.mcount != 0) {
			var badge = event.currentTarget.querySelector('.rongcloud-badge');
			if (badge) {
				badge.parentNode.removeChild(badge);
				clearUnreadCount(conversation.id);
			}
		}
		//打开会话
		openConversation(conversation, name);
	}

	//删除会话
	var removeConversation = function (targetId) {
		RongIMClient.getInstance().removeConversation(RongIMLib.ConversationType.PRIVATE, targetId, {
			onSuccess: function (bool) {
				console.log('删除会话成功');
				// 删除会话成功。
			},
			onError: function (error) {
				// error => 删除会话的错误码
			}
		});
	}

	//获取会话列表
	var getConversationList = function (callback) {
		RongIMClient.getInstance().getConversationList({
			onSuccess: function (list) {
				console.log(list);
				//用户信息处理 http://support.rongcloud.cn/kb/NjQ5
				var _list = [];
				for (var i = 0; i < list.length; i++) {
					if (list[i].conversationType == RongIMLib.ConversationType.PRIVATE) {
						_list.push(list[i]);
					}
				}
				var temp = _list[0];
				for (var i = 0; i < _list.length; i++) {
					for (var j = i + 1; j < _list.length; j++) {
						if (_list[i].sentTime < _list[j].sentTime) {
							var temp = _list[i];
							_list[i] = _list[j];
							_list[j] = temp;
						}
					}
				}
				var idList = new Array;
				for (var i = 0; i < _list.length; i++) {
					idList.push(parseInt(_list[i].targetId));
				}
				RCS.config.getUserInfo(idList, function (res) {
					if (res.length > 0) {
						for (var i = 0; i < res.length; i++) {
							_list[i].user = res[i];
						}
						callback && callback(_list);
					} else {
						callback && callback(_list);
					}
				})
			},
			onError: function (error) {
				console.error(error);
			}
		}, null);
	}

	//更新会话列表
	var updateConversationList = function () {
		var callback = function (list) {
			var obj = {};
			obj.list = list;
			$('.rcs-conversation-list')[0].innerHTML = render(templates.conversation, obj);
		}
		getConversationList(callback);
	}

	//清楚未读消息数
	var clearUnreadCount = function (targetId) {
		var conversationType = RongIMLib.ConversationType.PRIVATE;
		RongIMClient.getInstance().clearUnreadCount(conversationType, targetId, {
			onSuccess: function () {
				console.log('清除未读消息成功');
				// 清除未读消息成功。
			},
			onError: function (error) {
				// error => 清除未读消息数错误码。
			}
		});
	}

	//进入指定会话
	var openConversation = function (conversation, name) {
		conversation.targetType = RongIMLib.ConversationType.PRIVATE;
		var chat = $(".rcs-chat-wrapper")[0];
		var callbacks = function (list, hasMsg) {
			var data = {};
			var messageList = {};
			messageList.firstEnter = true;
			messageList.list = modificateMessage(list);

			data.messageList = render(templates.imMessage, messageList);
			data.targetName = name || conversation.id;
			data.terminal = terminal;
			$(".rcs-chat-wrapper")[0].innerHTML = render(templates.chat, data);
			scrollBottom();
			utils.hide($('.rongcloud-mode2')[0]);
			utils.show($('.rongcloud-mode1')[0]);
			//初始化表情
			var emojiList = emoji.getEmoji();
			var strHtml = '';
			for (var i = 0; i < emojiList.length; i++) {
				strHtml += '<div class="emojiItem">' + emojiList[i].outerHTML + '</div>';
			}
			$('.rongcloud-expressionContent')[0].innerHTML += strHtml;

			if (hasMsg) {
				$('.rongcloud-Messages-history')[0].style.display = 'block';
			}
		}
		var count = conversation.mcount < 2 ? 2 : (conversation.mcount > 20 ? 20 : conversation.mcount);
		getHisMessage(conversation.id, 0, parseInt(count), callbacks);
	}

	//拉去消息记录
	var getHisMessage = function (conversationId, timestrap, count, callbacks) {
		var conversationType = RongIMLib.ConversationType.PRIVATE; //私聊,其他会话选择相应的消息类型即可。
		var targetId = conversationId; // 想获取自己和谁的历史消息，targetId 赋值为对方的 Id。
		// timestrap默认传 null，若从头开始获取历史消息，请赋值为 0 ,timestrap = 0;
		// count每次获取的历史消息条数，范围 0-20 条，可以多次获取。
		RongIMLib.RongIMClient.getInstance().getHistoryMessages(conversationType, targetId, timestrap, count, {
			onSuccess: function (list, hasMsg) {
				conversation.messageContent = list.concat(conversation.messageContent);
				callbacks(list, hasMsg);
			},
			onError: function (error) {
				console.log("GetHistoryMessages,errorcode:" + error);
			}
		});
	}

	//单条消息修饰
	var modifyMessage = function (msg) {
		if (msg.messageType == 'TextMessage') {
			msg.content.content = textMessageFormat(msg.content.content);
		} else if (msg.messageType == 'FileMessage') {
			msg.content.size = utils.getFileSize(msg.content.size);
		} else if (msg.messageType == 'VoiceMessage') {
			RongIMLib.RongIMVoice.preLoaded(msg.content.content);
		}
		return msg;
	}

	//消息修饰，2条消息之间相差6000毫秒，显示消息发送时间
	var modificateMessage = function (list) {
		var listTemp = JSON.parse(JSON.stringify(list));
		var _list = [];
		for (var i = 0; i < listTemp.length; i++) {
			var messageTime = {
				sentTime: utils.getTime(listTemp[i].sentTime),
				messageType: 'TimeMessage'
			};
			var messageMap = [
				"TextMessage",
				"FileMessage",
				"SightMessage",
				"ImageMessage",
				"VoiceMessage",

			];
			if (messageMap.indexOf(listTemp[i].messageType) >= 0) {
				listTemp[i] = modifyMessage(listTemp[i]);
			} else {
				listTemp[i].messageType = 'UnknownMessage';
			}
			if (i == 0) {
				_list.push(messageTime);
			} else if (listTemp[i].sentTime - listTemp[i - 1].sentTime >= 60000) {
				_list.push(messageTime);
			}
			_list.push(listTemp[i]);
		}
		return _list;
	}

	//播放音频
	var play = function (event, msgContent) {
		RongIMLib.RongIMVoice.stop();
		var thisTarget = event.target || event.srcElement;
		if (thisTarget.className.indexOf('rongcloud-animate') != -1) {
			thisTarget.className = thisTarget.className.replace(' rongcloud-animate', '');
			clearTimeout(voicePlay);
		} else {
			var audioStatusNode = thisTarget.parentNode.querySelector('.rongcloud-audioState');
			if (audioStatusNode) {
				audioStatusNode.parentNode.removeChild(audioStatusNode);
			}
			if (voicePlay) {
				clearTimeout(voicePlay);
				var voiceList = $('.rongcloud-audioBox');
				for (var i = 0; i < voiceList.length; i++) {
					voiceList[i].className = 'rongcloud-audioBox rongcloud-clearfix';
				}
			}
			RongIMLib.RongIMVoice.play(msgContent.content, msgContent.duration);
			thisTarget.className = thisTarget.className + ' rongcloud-animate';
			voicePlay = setTimeout(function () {
				thisTarget.className = thisTarget.className.replace(' rongcloud-animate', '');
			}, msgContent.duration * 1000);
		}
	}

	//播放视频
	var playVideo = function (event) {
		var video = event.currentTarget.querySelector('video');
		var btn = event.currentTarget.querySelector('.play-btn');
		if (video.paused) {
			video.play();
			btn.style.display = "none";
		} else {
			video.pause();
			btn.style.display = "block";
		}
		video.onended = function () {
			btn.style.display = "block";
		}
	}


	//img上传图片
	var imgUpload = function (event) {
		var thisTarget = event.target || event.srcElement;
		var _file = thisTarget.files;
		for (var i = 0; i < _file.length; i++) {
			RCS.config.fileUpload(_file[i], "image", function (data) {
				console.log("文件上传完成：", data);
				var msg = messageItem["image"](data);
				sendMessage(msg);
			});
		}
		thisTarget.value = '';
	}
	//上传文件
	var fileUpload = function (event) {
		var thisTarget = event.target || event.srcElement;
		var _file = thisTarget.files;
		for (var i = 0; i < _file.length; i++) {
			RCS.config.fileUpload(_file[i], "file", function (data) {
				console.log("文件上传完成：", data);
				var msg = messageItem["file"](data);
				sendMessage(msg);
			});
		}
		thisTarget.value = '';
	}
	var messageItem = {
		file: function (file) {
			var name = file.name || '',
				index = name.lastIndexOf('.') + 1,
				type = name.substring(index);
			// 发送文件消息请参考： http://rongcloud.cn/docs/web_api_demo.html#发送消息
			// 创建文件消息
			return new RongIMLib.FileMessage({
				name: file.name,
				size: file.size,
				type: type,
				fileUrl: file.downloadUrl,
				user:RCS.config.user
			});
		},
		image: function (image) {
			return new RongIMLib.ImageMessage({
				content: image.thumbnail,
				imageUri: image.downloadUrl,
				user:RCS.config.user
			});
		}
	};

	//关闭聊天窗口
	var endConversation = function () {
		$('.rcs-chat-wrapper')[0].innerHTML = '';
	}

	//最小化
	var minimize = function () {
		utils.hide($('.customer-service')[0]);
	}

	//预览图片
	var viewImage = function (event) {
		var thisTarget = event.target || event.srcElement;
		var image = {
			imageUrl: thisTarget.getAttribute('data-img')
		}
		$('.imageViewBox')[0].innerHTML = render(templates.imageView, image);
		utils.fadein($('.imageViewBox')[0]);
	}
	var escImageView = function () {
		$('.imageViewBox')[0].innerHTML = '';
		utils.fadeout($('.imageViewBox')[0]);
	}

	//sdk初始化
	var sdkInit = function (params, callbacks) {
		var appKey = params.appKey;
		var token = params.token;
		var navi = params.navi || "";

		if (navi !== "") {
			//私有云
			var config = {
				navi: navi
			};
			console.log("私有云");
			console.log(params);
			RongIMLib.RongIMClient.init(appKey, null, config);
		} else {
			//公有云
			console.log("公有云");
			console.log(params);
			RongIMLib.RongIMClient.init(appKey);
		}

		var instance = RongIMClient.getInstance();

		// 连接状态监听器
		RongIMClient.setConnectionStatusListener({
			onChanged: function (status) {
				console.log(status);
				var connectDom = $('.rcs-connect-status')[0];
				if (connectDom) {
					connectDom.style.display = 'block';
				}
				switch (status) {
					case RongIMLib.ConnectionStatus.CONNECTED:
						if (connectDom) {
							connectDom.style.display = 'none';
						}
						callbacks.getInstance && callbacks.getInstance(instance);
						break;
					case RongIMLib.ConnectionStatus.CONNECTING:
						console.log('正在链接');
						break;
					case RongIMLib.ConnectionStatus.DISCONNECTED:
						console.log('断开连接');
						break;
					case RongIMLib.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT:
						console.log('其他设备登录');
						break;
					case RongIMLib.ConnectionStatus.DOMAIN_INCORRECT:
						console.log('域名不正确');
						break;
					case RongIMLib.ConnectionStatus.NETWORK_UNAVAILABLE:
						console.log('网络不可用');
						break;
					case RongIMLib.ConnectionStatus.DISCONNECTED:
						console.log('断开连接');
						break;
					case 4:
						console.log('token无效');
						break;
					default:
						console.log('未知错误');
						break;
				}
			}
		});

		RongIMClient.setOnReceiveMessageListener({
			// 接收到的消息
			onReceived: function (message) {
				// 判断消息类型
				console.log("新消息: " + message.targetId);
				if (message.offLineMessage) {
					return;
				}
				console.log(message);
				if (message.conversationType == RongIMLib.ConversationType.PRIVATE) {
					if (message.targetId == conversation.id) {
						updateMessage(message);
						clearUnreadCount(conversation.id);
					}
					updateConversationList();
				}
			}
		});

		//开始链接
		RongIMClient.connect(token, {
			onSuccess: function (userId) {
				callbacks.getCurrentUser && callbacks.getCurrentUser(userId);
				console.log("链接成功，用户id：" + userId);
				RCS.config.user.id = userId;
			},
			onTokenIncorrect: function () {
				console.log('token无效');
			},
			onError: function (errorCode) {
				console.log("=============================================");
				console.log(errorCode);
			}
		});
	}

	//创建button
	var createButton = function (config) {
		var div = document.createElement('div');
		div.className = "customer-service";
		div.id = "customer-service";
		div.setAttribute('style', "display: none;");
		div.innerHTML =  render(templates.button);
		config.target.appendChild(div); 
		createIMConversation(config);
		addListener(config);
	}

	var addListener = function (config) {
		var callback = function (phoneOrPc) {
			terminal = phoneOrPc;
		}
		utils.browserRedirect(callback);
		if (terminal == 'pc') {
			document.body.onclick = function () {
				hideEmoji();
			}
			if (Notification.permission === "granted") {
				supportNot = true;
			}
			// Otherwise, we need to ask the user for permission
			else if (Notification.permission !== "denied") {
				Notification.requestPermission(function (permission) {
					// If the user accepts, let's create a notification
					if (permission === "granted") {
						supportNot = true;
					}
				});
			}
		} else {
			document.body.ontouchstart = function (event) {
				if (event.target.className.indexOf('emojiItem') < 0 && event.target.className.indexOf('rong-emoji-content') < 0 && event.target.className.indexOf('rongcloud-expressionContent') < 0) {
					hideEmoji();
				}
				if (event.target.className.indexOf('rongcloud-rong-btn') < 0 && event.target.className.indexOf('rongcloud-text') < 0) {
					var inputMsg = $(".rongcloud-text")[0];
					if (inputMsg) {
						inputMsg.blur();
					}
				}
			}
		}
	}

	var hideEmoji = function () {
		var emojiContent = $('.rongcloud-expressionWrap')[0];
		if (emojiContent) {
			utils.hide(emojiContent);
		}
	}

	//button点击事件
	var showCommon = function () {
		var csContext = $('.customer-service')[0];
		if (csContext.style.display == 'none') {
			utils.show($('.customer-service')[0]);
		} else {
			utils.hide($('.customer-service')[0]);
		}
	}


	//im组件初始化
	var init = function (config) {
		RCS.config = config;
		config.isIM = true;
		var callbacks = {
			getInstance: function (instance) {
				
				//加载模板
				getTemplates();
				//融云聊天初始化
				emoji.init();
				//添加模块
				createButton(config);
			}
		}
		sdkInit(config, callbacks);
	}

	//H5唤醒键盘的时候输入框显示在视野内
	var keyboard = function (event) {
		var thisTarget = event.target || event.srcElement;
		setTimeout(function () {
			thisTarget.scrollIntoView(true);
		}, 500)
	}
	//对外暴露
	RCS.init = init;
	RCS.send = send;
	RCS.keySend = keySend;
	RCS.showemoji = showemoji;
	RCS.chooseEmoji = chooseEmoji;
	RCS.loadHisMessages = loadHisMessages;
	RCS.scrollBottom = scrollBottom;
	RCS.imgUpload = imgUpload;
	RCS.fileUpload = fileUpload;
	RCS.endConversation = endConversation;
	RCS.play = play;
	RCS.playVideo = playVideo;
	RCS.minimize = minimize;
	RCS.showCommon = showCommon;
	RCS.confirm = confirm;
	RCS.close = close;
	RCS.viewImage = viewImage;
	RCS.escImageView = escImageView;
	RCS.keyboard = keyboard;
	RCS.startConversation = startConversation;
})(RCS);