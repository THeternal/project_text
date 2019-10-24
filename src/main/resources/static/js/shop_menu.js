var host = window.location.protocol + "//" + window.location.host;

var shop_navs = [
		{
			"children" : [],
			"href" : "" + host + "/daiken/shop/order/accept_order_page",
			"icon" : "fa-truck",
			"title" : "发货管理"
		},
		{
			"children" : [
					{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/order/after_sale_order_page",
						"icon" : "fa-envelope-open-o",
						"title" : "退货待收订单列表"

					},
					{
						"children" : [],
						"href" : ""
								+ host
								+ "/daiken/shop/order/after_sale_compensate_order_page",
						"icon" : "fa-gift",
						"title" : "补偿订单列表"

					}, ],
			"href" : "",
			"icon" : "fa-headphones",
			"title" : "售后管理"
		},
		{
			"children" : [],
			"href" : "" + host + "/daiken/shop/order/",
			"icon" : "fa-laptop",
			"title" : "订单管理"
		},
		{
			"children" : [],
			"href" : "" + host + "/daiken/shop/goods/new",
			"icon" : "fa-cubes",
			"title" : "商品管理"
		},
		{
			"children" : [],
			"href" : "" + host + "/daiken/shop/goods/marketing_page",
			"icon" : "fa-life-ring",
			"title" : "店铺营销"
		},
		{
			"children" : [
					{
						"children" : [],
						"href" : "" + host + "/daiken/shop/goods/income_page",
						"icon" : "fa-level-down",
						"title" : "商品收入报表"

					},
					{
						"children" : [],
						"href" : ""
								+ host
								+ "/daiken/shop/goods/purchasing_page",
						"icon" : "fa-level-down",
						"title" : "商品代卖报表"

					},
					{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/goods/red_share_page",
						"icon" : "fa-envelope",
						"title" : "商品红包报表"
					},{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/goods/precision_push_page",
						"icon" : "fa-envelope",
						"title" : "精准推送报表"
					},

					{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/goods/promotion_page",
						"icon" : "fa-hand-peace-o",
						"title" : "促销商品报表"
					}, {
						"children" : [],
						"href" : "" + host + "/daiken/shop/goods/sale_total_page",
						"icon" : "fa-cubes",
						"title" : "总销售收入报表"
					}, {
						"children" : [],
						"href" : "" + host + "/daiken/shop/order/detailed",
						"icon" : "fa-cubes",
						"title" : "订单详细报表"
					},{
						"children" : [],
						"href" : "" + host + "/daiken/shop/order/after_sale_page",
						"icon" : "fa-cubes",
						"title" : "退货详细报表"
					}  ],
			"href" : "",
			"icon" : "fa-database",
			"title" : "数据报表"
		},
		{
			"children" : [],
			"href" : "" + host + "/daiken/shop/info/",
			"icon" : "fa-home",
			"title" : "店铺管理"
		},

		{
			"children" : [ {
				"children" : [],
				"href" : "" + host + "/daiken/shop/invest/",
				"icon" : "fa-sliders",
				"title" : "调研总列表"

			}, {
				"children" : [],
				"href" : "" + host + "/daiken/shop/invest/like_page",
				"icon" : "fa-thumbs-up",
				"title" : "点赞列表"
			},

			{
				"children" : [],
				"href" : "" + host + "/daiken/shop/invest/vote_page",
				"icon" : "fa-paper-plane-o",
				"title" : "投票列表"
			}, {
				"children" : [],
				"href" : "" + host + "/daiken/shop/invest/question_page",
				"icon" : "fa-question-circle-o",
				"title" : "问卷调查列表"
			} ],
			"href" : "",
			"icon" : "fa-keyboard-o",
			"title" : "调研管理"
		},
		{
			"children" : [
					{
						"children" : [],
						"href" : "" + host + "/daiken/shop/invest/chart/result",
						"icon" : "fa-pie-chart",
						"title" : "结果统计"
					},
					{
						"children" : [],
						"href" : "" + host + "/daiken/shop/invest/like_report",
						"icon" : "fa-thumbs-o-up",
						"title" : "点赞报表"
					},
					{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/invest/question_report",
						"icon" : "fa-paper-plane-o",
						"title" : "投票报表"
					},
					{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/invest/question_report",
						"icon" : "fa-question-circle-o",
						"title" : "问卷调查报表"
					},
					{
						"children" : [],
						"href" : "" + host
								+ "/daiken/shop/invest/chart/invest_count_page",
						"icon" : "fa-pie-chart",
						"title" : "调研统计"
					} ],
			"href" : "",
			"icon" : "fa-pie-chart",
			"title" : "调研统计"
		}, {
			"children" : [],
			"href" : "" + host + "/daiken/shop/goods/push_user_page",
			"icon" : "fa-users",
			"title" : "精准送达列表"
		}, {
			"children" : [ {
				"children" : [],
				"href" : "" + host + "/daiken/shop/info/recharge_page",
				"icon" : "fa-money",
				"title" : "充值记录"
			}, {
				"children" : [],
				"href" : "" + host + "/daiken/shop/info/withdraw_page",
				"icon" : "fa-usd",
				"title" : "提现记录"
			} ],
			"href" : "",
			"icon" : "fa-jpy",
			"title" : "平台流水记录"
		}, {
			"children" : [],
			"href" : "" + host + "/daiken/shop/info/reply_page",
			"icon" : "fa-comments-o",
			"title" : "默认消息设置"
		} ];