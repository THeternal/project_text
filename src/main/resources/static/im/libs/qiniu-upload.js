var calcPosition = function (width, height, opts) {
	var isheight = width < height;
	var scale = isheight ? height / width : width / height;
	var zoom, x = 0,
		y = 0,
		w, h;

	var gtScale = function () {
		if (isheight) {
			zoom = width / 100;
			w = 100;
			h = height / zoom;
			y = (h - opts.maxHeight) / 2;
		} else {
			zoom = height / 100;
			h = 100;
			w = width / zoom;
			x = (w - opts.maxWidth) / 2;
		}
		return {
			w: w,
			h: h,
			x: -x,
			y: -y
		};
	};

	var ltScale = function () {
		if (isheight) {
			zoom = height / opts.maxHeight;
			h = opts.maxHeight;
			w = width / zoom;
		} else {
			zoom = width / opts.maxWidth;
			w = opts.maxWidth;
			h = height / zoom;
		}
		return {
			w: w,
			h: h,
			x: -x,
			y: -y
		};
	};
	return scale > opts.scale ? gtScale() : ltScale();
};

var getBlobUrl = function (file) {
	var URL = window.URL || window.webkitURL;
	return URL ? URL.createObjectURL(file) : "";
};

var getthumbnail = function (file, opts, callback) {
	var canvas = document.createElement("canvas"),
		context = canvas.getContext('2d');
	var img = new Image();
	img.onload = function () {
		var pos = calcPosition(img.width, img.height, opts);
		canvas.width = pos.w > opts.maxWidth ? opts.maxWidth : pos.w;
		canvas.height = pos.h > opts.maxHeight ? opts.maxHeight : pos.h;
		context.drawImage(img, pos.x, pos.y, pos.w, pos.h);
		try {
			var base64 = canvas.toDataURL(file.type, opts.quality);
			var reg = new RegExp('^data:image/[^;]+;base64,');
			base64 = base64.replace(reg, '');
			callback(base64);
		} catch (e) {
			throw new Error(e);
		}
	};
	img.src = typeof file == 'string' ? 'data:image/jpg;base64,' + file : getBlobUrl(file);
};

function getToken(callback) {
	//获取七牛云上传token
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET", "https://dev.kemean.net/needle/api/common/qn_upload", true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = () => {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				var data = JSON.parse(xmlhttp.response);
				callback(data.data);
			} else {
				console.error("获取七牛云上传token失败");
			}
		}
	}
}
//文件名称随机数
function randomChar(l, url = "") {
	const x = "0123456789qwertyuioplkjhgfdsazxcvbnm";
	var tmp = "";
	var time = new Date();
	for (var i = 0; i < l; i++) {
		tmp += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
	}
	return (
		"file/upload/" +
		time.getFullYear() +
		time.getMonth() +
		"/" +
		time.getDate() +
		"/" +
		url +
		time.getTime() +
		tmp
	);
};

function $fileUpload(file, type, callback) {
	var name = file.name;
	var size = file.size;
	var type = file.type;
	var config = {
		//表示是否使用 cdn 加速域名
		useCdnDomain: true,
		//qiniu.region.z0: 代表华东区域
		// qiniu.region.z1: 代表华北区域
		// qiniu.region.z2: 代表华南区域
		// qiniu.region.na0: 代表北美区域
		// qiniu.region.as0: 代表东南亚区域
		region: qiniu.region.z2
		//是否禁用日志报告
		// disableStatisticsReport: false
	};
	var putExtra = {
		//文件原文件名
		fname: "",
		// 用来放置自定义变量
		params: {},
		// 用来限制上传文件类型 ,限制类型放到数组里： ["image/png", "image/jpeg", "image/gif"]
		mimeType: [] || null
	};
	var downloadUrl = "",
		thumbnail = "";
	var opts = {
		maxWidth: 140,
		maxHeight: 140,
		scale: 20,
		quality: 8
	}
	getthumbnail(file, opts, function (dara) {
		if (downloadUrl) {
			callback({
				downloadUrl: downloadUrl,
				thumbnail: dara
			});
		} else {
			thumbnail = dara;
		}
	});
	getToken(function (dara) {
		console.log(dara);
		//文件上传配置
		var observable = qiniu.upload(
			file,
			type == "iamge" ? randomChar(8) : name,
			dara.token,
			putExtra,
			config
		);
		//文件开始上传
		var subscription = observable.subscribe({
			next(res) {
				//图片上传进度
				console.log("图片上传进度", res.total.percent);
			},
			error(err) {
				//图片报错
				console.error(err);
			},
			complete(res) {
				if (thumbnail) {
					callback({
						downloadUrl: dara.visitPrefix + res.key,
						thumbnail: thumbnail,
						name: name,
						size: size,
						type: type
					});
				} else {
					downloadUrl = dara.visitPrefix + res.key;
				}
			}
		});
	});

}