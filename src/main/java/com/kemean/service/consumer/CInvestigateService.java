package com.kemean.service.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.kemean.bean.DaikenInvestigate;
import com.kemean.bean.DaikenInvestigateOperation;
import com.kemean.bean.DaikenInvestigateOptions;
import com.kemean.bean.DaikenInvestigateQuestion;
import com.kemean.bean.DaikenInvestigateQuestionUser;
import com.kemean.bean.DaikenOrder;
import com.kemean.bean.DaikenRedShareGet;
import com.kemean.bean.DaikenUser;
import com.kemean.bean.KemeanConfig;
import com.kemean.constant.DaikenApiResultTips;
import com.kemean.constant.DaikenConfigEnum;
import com.kemean.constant.DaikenConstant;
import com.kemean.constant.DaikenFinanceTypeEnum;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateJumpTypeEnum;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateQuestionTypeEnum;
import com.kemean.constant.DaikenInvestigate.DaikenInvestigateTypeEnum;
import com.kemean.constant.DaikenOperationTypeExplain;
import com.kemean.constant.DaikenRedisKeyEnum;
import com.kemean.constant.KemeanConstant;
import com.kemean.constant.KemeanDateFormatEnum;
import com.kemean.constant.KemeanOrderEnum;
import com.kemean.constant.KemeanPayTypeEnum;
import com.kemean.constant.KemeanResultEnum;
import com.kemean.dao.DaikenInvestigateDao;
import com.kemean.dao.DaikenInvestigateOperationDao;
import com.kemean.dao.DaikenInvestigateOptionsDao;
import com.kemean.dao.DaikenInvestigateQuestionDao;
import com.kemean.dao.DaikenInvestigateQuestionUserDao;
import com.kemean.dao.DaikenOrderDao;
import com.kemean.dao.DaikenRedShareGetDao;
import com.kemean.dao.DaikenUserDao;
import com.kemean.service.KemeanRedisService;
import com.kemean.service.common.CommonService;
import com.kemean.service.common.UserService;
import com.kemean.util.DaikenUtil;
import com.kemean.util.KemeanUtilAid;
import com.kemean.vo.bo.KemeanPageApiBO;
import com.kemean.vo.bo.KemeanResult;
import com.kemean.vo.bo.c.investigate.InvestigateAwardBO;
import com.kemean.vo.bo.c.investigate.InvestigateListBO;
import com.kemean.vo.bo.c.investigate.InvestigateOrderBO;
import com.kemean.vo.bo.c.investigate.LikeInfoBO;
import com.kemean.vo.bo.c.investigate.LikeInfoOverBO;
import com.kemean.vo.bo.c.investigate.QuestionInfoBO;
import com.kemean.vo.bo.c.investigate.QuestionnaireInfoBO;
import com.kemean.vo.bo.c.investigate.RecordAnswerBO;
import com.kemean.vo.bo.c.investigate.VoteInfoBO;
import com.kemean.vo.bo.c.investigate.VoteInfoOverBO;
import com.kemean.vo.bo.c.investigate.VoteInfoOverPlyerBO;
import com.kemean.vo.bo.c.investigate.VoteInfoPlyearBO;
import com.kemean.vo.bo.c.investigate.VotePlyerInfoBO;
import com.kemean.vo.po.c.investigate.InvestigateListPO;
import com.kemean.vo.po.c.investigate.LikePO;
import com.kemean.vo.po.c.investigate.PostLikePO;
import com.kemean.vo.po.c.investigate.PostVotePO;
import com.kemean.vo.po.c.investigate.PostVotePlyearPO;
import com.kemean.vo.po.c.investigate.QuestionInfoPO;
import com.kemean.vo.po.c.investigate.QuestionInvestigateInfoPO;

/**
 * 【客户端】 调研业务层
 * 
 * @Date 2018年6月7日
 *
 * @company 深圳科名网络有限公司 {@link www.kemean.com}
 */
@Service
public class CInvestigateService {

	@Autowired
	private DaikenInvestigateDao daikenInvestigateDao;

	@Autowired
	private DaikenInvestigateOptionsDao daikenInvestigateOptionsDao;

	@Autowired
	private DaikenInvestigateOperationDao daikenInvestigateOperationDao;

	@Autowired
	private DaikenOrderDao daikenOrderDao;

	@Autowired
	private DaikenUserDao daikenUserDao;

	@Autowired
	private DaikenInvestigateQuestionDao daikenInvestigateQuestionDao;

	@Autowired
	private DaikenRedShareGetDao daikenRedShareGetDao;

	@Autowired
	private DaikenInvestigateQuestionUserDao daikenInvestigateQuestionUserDao;

	@Autowired
	private KemeanRedisService kemeanRedisService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private UserService userService;

	/**
	 * 调研列表
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public KemeanPageApiBO<List<InvestigateListBO>> investigateList(InvestigateListPO investigateListPO,
			DaikenUser daikenUser) {
		Integer investigateStatus = investigateListPO.getInvestigateStatus() == null
				? DaikenInvestigateTypeEnum.ALL_INVESTIGATE.getType()
				: investigateListPO.getInvestigateStatus();
		String descStr = "";
		Boolean rewardPrice = false;
		if (investigateListPO.getBonus() != null) {
			if (investigateListPO.getBonus()) {
				rewardPrice = investigateListPO.getBonus();
				descStr += " reward_price, ";
			}
		}
		if (investigateListPO.getReleaseDate() != null) {
			if (investigateListPO.getReleaseDate()) {
				descStr += " pay_time, ";
			}
		}
		if (StringUtils.isNotBlank(descStr)) {
			descStr = descStr.substring(0, descStr.length() - 2);
		}
		Boolean isOver = false;
		if (investigateListPO.getIsOver() != null) {
			isOver = investigateListPO.getIsOver();
		}
		List<DaikenInvestigate> dbInvestigates = daikenInvestigateDao.investigateList(investigateStatus, descStr,
				isOver, investigateListPO.getPageNo(), investigateListPO.getPageSize(), rewardPrice);
		List<InvestigateListBO> result = new ArrayList<InvestigateListBO>(dbInvestigates.size());
		if (CollectionUtils.isEmpty(dbInvestigates)) {
			return new KemeanPageApiBO<List<InvestigateListBO>>(0l, 1, result);
		}
		Date dateNow = new Date();
		for (DaikenInvestigate daikenInvestigate : dbInvestigates) {
			InvestigateListBO bo = new InvestigateListBO();
			BeanUtils.copyProperties(daikenInvestigate, bo);
			bo.setIsOver(false);
			Integer dateDifference = DaikenUtil.dateDifference(dateNow, daikenInvestigate.getEndTime());
			if (dateDifference <= 0) {
				bo.setIsOver(true);
			}
			if (!daikenInvestigate.getType().equals(DaikenInvestigateTypeEnum.QUESTIONNAIRE.getType())) {
				result.add(bo);
			}
			if (daikenInvestigate.getType().equals(DaikenInvestigateTypeEnum.QUESTIONNAIRE.getType())) {
				DaikenInvestigateQuestionUser dbQuestionUser = daikenInvestigateQuestionUserDao
						.selectUniqueEntityByProperties(
								new String[] { KemeanConstant.DATA_DELETED, "investigateId", "userId" },
								new Object[] { false, daikenInvestigate.getId(), daikenUser.getId() });
				if (dbQuestionUser != null) {
					result.add(bo);
				}
			}
			if (daikenInvestigate.getPayStatus()) {
				bo.setCreateTime(daikenInvestigate.getPayTime());
			}
		}
		PageInfo<DaikenInvestigate> pageInfo = new PageInfo<DaikenInvestigate>(dbInvestigates);
		return new KemeanPageApiBO<List<InvestigateListBO>>(pageInfo.getTotal(), pageInfo.getPages(), result);
	}

	/**
	 * 发布（修改）投票
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	@Transactional
	public KemeanResult<InvestigateOrderBO> postVote(PostVotePO postVotePO, DaikenUser loginConsumer, Integer type) {
		Date now = new Date();
		DaikenInvestigate investigate = null;
		boolean flag = false;
		if (postVotePO.getObjId() == null) {
			flag = true;
		}

		if (flag) {
			investigate = new DaikenInvestigate();
			investigate.setType(DaikenInvestigateTypeEnum.POST_VOTE.getType());
			investigate.setJumpTypeId(0);
			investigate.setJumpType(DaikenInvestigateJumpTypeEnum.NO_JUMP.getType());
			// 默认为未支付状态
			investigate.setPayStatus(false);
		}
		if (!flag) {
			investigate = daikenInvestigateDao.selectById(postVotePO.getObjId());
		}

		BeanUtils.copyProperties(postVotePO, investigate);
		if (CollectionUtils.isNotEmpty(postVotePO.getAdvertisingImg())) {
			investigate.setAdvertisingImg(DaikenUtil.parseJSONArrayByList(postVotePO.getAdvertisingImg()).toString());
		}
		if (CollectionUtils.isNotEmpty(postVotePO.getInvestigateImg())) {
			investigate.setInvestigateImg(DaikenUtil.parseJSONArrayByList(postVotePO.getInvestigateImg()).toString());
		}
		investigate.setUserId(loginConsumer.getId());
		investigate.setUserUId(loginConsumer.getUid());
		investigate.setUpdateTime(now);
		Double pricePay = postVotePO.getMaxPeopleNum() * postVotePO.getRewardPrice();
		// 新建
		if (flag) {
			investigate.setCreateTime(now);
			if (DaikenConstant.back_ground_issue.equals(type) || DaikenConstant.shop_back_ground_issue.equals(type)) {
				// 用户剩余余额
				Double userPrice = userService.getConsumerOrBusinessBlance(loginConsumer);
				investigate.setPayStatus(false);
				// 总后台发布，需要加个平台管理费
				if (DaikenConstant.back_ground_issue.equals(type)) {
					KemeanConfig config = commonService.getConfig(DaikenConfigEnum.INVESTIGATE_CHARGE);
					Double investigateCharge = Double.valueOf(config.getRecord());
					pricePay = pricePay + investigateCharge;
				}

				if (userPrice < pricePay) {
					return new KemeanResult<>(false, "您的余额不足,请充值");
				}
				investigate.setPayStatus(true);
				investigate.setPayTime(new Date());
				daikenInvestigateDao.saveSelective(investigate);
				// 客户端，商户端 生成订单流水
				String orderNo = DaikenUtil.getOrderNo(now);
				commonService.createFinanceOrder(orderNo, DaikenFinanceTypeEnum.CHARGE.getType(), pricePay,
						loginConsumer.getId(), 0, "【平台发布调研投票】", "");

			}

			if (DaikenConstant.front_desk_issue.equals(type)) {
				daikenInvestigateDao.saveSelective(investigate);
			}
		}

		if (!flag) {
			daikenInvestigateDao.updateByPrimaryKeySelective(investigate);
		}

		List<PostVotePlyearPO> postVotePlyearLists = postVotePO.getPostVotePlyearLists();
		if (CollectionUtils.isNotEmpty(postVotePlyearLists)) {
			for (PostVotePlyearPO postVotePlyearPO : postVotePlyearLists) {
				DaikenInvestigateOptions investigateOperation = new DaikenInvestigateOptions();
				if (postVotePlyearPO.getObjId() == null) {
					flag = true;
				}
				BeanUtils.copyProperties(postVotePlyearPO, investigateOperation);
				if (flag) {
					investigateOperation.setInvestigateId(investigate.getId());
					investigateOperation.setCreateTime(now);
				} else {
					investigateOperation = daikenInvestigateOptionsDao.selectById(postVotePlyearPO.getObjId());
				}
				investigateOperation.setImg(DaikenUtil.parseJSONArrayByList(postVotePlyearPO.getImg()).toString());
				investigateOperation.setUpdateTime(now);
				if (flag) {
					daikenInvestigateOptionsDao.saveSelective(investigateOperation);

				}
				if (!flag) {
					daikenInvestigateOptionsDao.updateByPrimaryKeySelective(investigateOperation);
				}
			}
		}

		if (flag) {
			return new KemeanResult<>(true, "发布成功");
		}
		if (!flag) {
			return new KemeanResult<>(true, "修改成功");
		}

		// 前台发布
		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "idInvestigate" },
				new Object[] { false, investigate.getId() });
		if (dbOrder == null) {
			// 生成订单
			String orderNo = DaikenUtil.getOrderNo(now);
			// TODO 如果是测试版的话 支付0.01
			KemeanConfig config = commonService.getConfig(DaikenConfigEnum.PROJECT_VERSION);
			if (config.getRecord().equals("测试")) {
				pricePay = 0.01;
			}
			DaikenOrder newOrder = createInvestigateOrder(orderNo, investigate.getId(), loginConsumer, pricePay, now);
			daikenOrderDao.saveSelective(newOrder);
			InvestigateOrderBO bo = new InvestigateOrderBO();
			bo.setOrderNo(orderNo);
			bo.setPrice(pricePay);
			bo.setPayStatus(investigate.getPayStatus());
			return new KemeanResult<InvestigateOrderBO>(bo);
		}

		InvestigateOrderBO bo = new InvestigateOrderBO();
		bo.setOrderNo(dbOrder.getOrderNo());
		bo.setPrice(dbOrder.getPricePay());
		bo.setPayStatus(investigate.getPayStatus());
		return new KemeanResult<InvestigateOrderBO>(bo);
	}

	/**
	 * 发布（修改）点赞
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	@Transactional
	public KemeanResult<InvestigateOrderBO> postLike(PostLikePO postLikePO, DaikenUser loginConsumer, Integer type) {
		Date now = new Date();
		DaikenInvestigate investigate = null;
		boolean flag = false;
		// flag = true 新创建，flag = false 是 修改
		if (postLikePO.getObjId() == null) {
			flag = true;
		}
		if (flag) {
			investigate = new DaikenInvestigate();
			investigate.setType(DaikenInvestigateTypeEnum.POST_LIKE.getType());
			investigate.setJumpTypeId(0);
			investigate.setJumpType(DaikenInvestigateJumpTypeEnum.NO_JUMP.getType());
			// 默认为未支付状态
			investigate.setPayStatus(false);
		} else {
			investigate = daikenInvestigateDao.selectById(postLikePO.getObjId());
		}
		BeanUtils.copyProperties(postLikePO, investigate);
		if (CollectionUtils.isNotEmpty(postLikePO.getAdvertisingImg())) {
			investigate.setAdvertisingImg(DaikenUtil.parseJSONArrayByList(postLikePO.getAdvertisingImg()).toString());
		}
		if (CollectionUtils.isNotEmpty(postLikePO.getInvestigateImg())) {
			investigate.setInvestigateImg(DaikenUtil.parseJSONArrayByList(postLikePO.getInvestigateImg()).toString());
		}
		investigate.setUserId(loginConsumer.getId());
		investigate.setUserUId(loginConsumer.getUid());
		investigate.setCreateTime(now);
		investigate.setUpdateTime(now);
		Double pricePay = postLikePO.getMaxPeopleNum() * postLikePO.getRewardPrice();
		// 新建
		if (flag) {
			if (DaikenConstant.back_ground_issue.equals(type) || DaikenConstant.shop_back_ground_issue.equals(type)) {
				Double balance = userService.getConsumerOrBusinessBlance(loginConsumer);
				// 总后台发布，需要加个平台管理费
				if (DaikenConstant.back_ground_issue.equals(type)) {
					KemeanConfig config = commonService.getConfig(DaikenConfigEnum.INVESTIGATE_CHARGE);
					Double investigateCharge = Double.valueOf(config.getRecord());
					pricePay = pricePay + investigateCharge;
				}
				if (balance < pricePay) {
					return new KemeanResult<>(false, "您的余额不足,请充值");
				}
				investigate.setPayStatus(true);
				investigate.setPayTime(new Date());
				daikenInvestigateDao.saveSelective(investigate);
				// 生成订单流水
				String orderNo = DaikenUtil.getOrderNo(now);
				// 客户端，商户端
				commonService.createFinanceOrder(orderNo, DaikenFinanceTypeEnum.CHARGE.getType(), pricePay,
						loginConsumer.getId(), 0, "【平台发布调研点赞】", "");
				return new KemeanResult<>(true, "发布成功");
			}

			if (DaikenConstant.front_desk_issue.equals(type)) {
				daikenInvestigateDao.saveSelective(investigate);
			}
		}

		// 修改
		if (!flag) {
			daikenInvestigateDao.updateByPrimaryKeySelective(investigate);
			return new KemeanResult<>(true, "修改成功");
		}

		DaikenOrder dbOrder = daikenOrderDao.selectUniqueEntityByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "idInvestigate" },
				new Object[] { false, investigate.getId() });
		if (dbOrder == null) {
			// 前台发布
			if (DaikenConstant.front_desk_issue.equals(type)) {
				// 生成订单
				String orderNo = DaikenUtil.getOrderNo(now);
				DaikenOrder newOrder = createInvestigateOrder(orderNo, investigate.getId(), loginConsumer, pricePay,
						now);
				daikenOrderDao.saveSelective(newOrder);
				InvestigateOrderBO bo = new InvestigateOrderBO();
				bo.setOrderNo(orderNo);
				bo.setPrice(pricePay);
				bo.setPayStatus(investigate.getPayStatus());
				return new KemeanResult<InvestigateOrderBO>(bo);
			}
		}

		InvestigateOrderBO bo = new InvestigateOrderBO();
		bo.setOrderNo(dbOrder.getOrderNo());
		bo.setPrice(dbOrder.getPricePay());
		bo.setPayStatus(investigate.getPayStatus());
		return new KemeanResult<InvestigateOrderBO>(bo);
	}

	/**
	 * 点赞详情
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public LikeInfoBO likeInfo(Integer objId) {
		LikeInfoBO bo = new LikeInfoBO();
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(objId);
		dbInvestigate.setNumVisit(dbInvestigate.getNumVisit() + 1);
		daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
		BeanUtils.copyProperties(dbInvestigate, bo);
		if (StringUtils.isNotBlank(dbInvestigate.getAdvertisingImg())) {
			bo.setAdvertisingImg(JSONArray.parseArray(dbInvestigate.getAdvertisingImg(), String.class));
		} else {
			bo.setAdvertisingImg(Arrays.asList());
		}

		if (StringUtils.isNotBlank(dbInvestigate.getInvestigateImg())) {
			bo.setInvestigateImg(JSONArray.parseArray(dbInvestigate.getInvestigateImg(), String.class));
		} else {
			bo.setInvestigateImg(Arrays.asList());
		}

		bo.setIsOver(false);
		Integer dateDifference = DaikenUtil.dateDifference(new Date(), dbInvestigate.getEndTime());
		if (dateDifference <= 0) {
			bo.setIsOver(true);
		}

		return bo;
	}

	/**
	 * 投票详情
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public VoteInfoBO voteInfo(Integer objId) {
		VoteInfoBO bo = new VoteInfoBO();
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(objId);
		dbInvestigate.setNumVisit(dbInvestigate.getNumVisit() + 1);
		daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);

		BeanUtils.copyProperties(dbInvestigate, bo);
		if (StringUtils.isNotBlank(dbInvestigate.getAdvertisingImg())) {
			bo.setAdvertisingImg(JSONArray.parseArray(dbInvestigate.getAdvertisingImg(), String.class));
		} else {
			bo.setAdvertisingImg(Arrays.asList());
		}
		if (StringUtils.isNotBlank(dbInvestigate.getInvestigateImg())) {
			bo.setInvestigateImg(JSONArray.parseArray(dbInvestigate.getInvestigateImg(), String.class));
		} else {
			bo.setInvestigateImg(Arrays.asList());
		}

		bo.setIsOver(false);
		Integer dateDifference = DaikenUtil.dateDifference(new Date(), dbInvestigate.getEndTime());
		if (dateDifference <= 0) {
			bo.setIsOver(true);
		}
		List<DaikenInvestigateOptions> dbInvestigateOptions = daikenInvestigateOptionsDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "investigateId" }, new Object[] { false, objId });

		List<VoteInfoPlyearBO> voteInfoPlyearList = new ArrayList<VoteInfoPlyearBO>();
		if (CollectionUtils.isNotEmpty(dbInvestigateOptions)) {
			for (DaikenInvestigateOptions daikenInvestigateOptions : dbInvestigateOptions) {
				VoteInfoPlyearBO plyearBo = new VoteInfoPlyearBO();
				BeanUtils.copyProperties(daikenInvestigateOptions, plyearBo);
				plyearBo.setImg(JSONArray.parseArray(daikenInvestigateOptions.getImg(), String.class));
				voteInfoPlyearList.add(plyearBo);
			}
		}
		bo.setVoteInfoPlyearList(voteInfoPlyearList);
		return bo;
	}

	/**
	 * 投票选手信息
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public VotePlyerInfoBO votePlyerInfo(Integer objId) {
		VotePlyerInfoBO bo = new VotePlyerInfoBO();
		DaikenInvestigateOptions dbInvestigateOptions = daikenInvestigateOptionsDao.selectById(objId);
		BeanUtils.copyProperties(dbInvestigateOptions, bo);
		bo.setImg(JSONArray.parseArray(dbInvestigateOptions.getImg(), String.class));
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(dbInvestigateOptions.getInvestigateId());
		BeanUtils.copyProperties(dbInvestigate, bo);
		return bo;
	}

	/**
	 * 投票
	 * 
	 * @author huwei
	 * @date 2018年6月9日
	 */
	public KemeanResult<InvestigateAwardBO> voteInvestigatePlyerInfo(Integer objId, DaikenUser daikenUser)
			throws InterruptedException {
		Date now = new Date();
		DaikenInvestigateOptions dbInvestigateOptions = daikenInvestigateOptionsDao.selectById(objId);
		// 一个用户只能一个调研表操作一次
		List<DaikenInvestigateOperation> dbInvestigateOperations = daikenInvestigateOperationDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "investigateId", "userId" },
				new Object[] { false, dbInvestigateOptions.getInvestigateId(), daikenUser.getId() });
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(dbInvestigateOptions.getInvestigateId());
		if (CollectionUtils.isNotEmpty(dbInvestigateOperations)) {
			return new KemeanResult<>(false, DaikenApiResultTips.Investigate.INVESTIGATE_OPERATION_REPETITION);
		}
		// 【乐观锁】 获取当前版本
		boolean flag = false;
		for (int i = 0; i < 3; i++) {
			Integer happyLock = daikenInvestigateDao.updateByHappyLock(dbInvestigate.getId(),
					dbInvestigate.getDataVersion());
			if (happyLock > 0) {
				flag = true;
				break;
			}
			Thread.sleep(1000);
			// 重新获取版本，尝试再次操作
			dbInvestigate = daikenInvestigateDao.selectById(dbInvestigate.getId());
		}
		// 数据版本已经增加，操作失败
		if (!flag) {
			return new KemeanResult<>(false, DaikenApiResultTips.Investigate.INVESTIGATE_OPERATION_REPETITION);
		}
		if (dbInvestigate.getMaxPeopleNum() != 0) {
			if (dbInvestigate.getSalesNum() >= dbInvestigate.getMaxPeopleNum()) {
				return new KemeanResult<>(false, DaikenApiResultTips.Investigate.MAX_PEOPLE_NUM);
			}
		}
		dbInvestigateOptions.setVotes(dbInvestigateOptions.getVotes() + 1);
		dbInvestigateOptions.setUpdateTime(now);
		daikenInvestigateOptionsDao.updateByPrimaryKeySelective(dbInvestigateOptions);
		dbInvestigate.setSalesNum(dbInvestigate.getSalesNum() + 1);
		// 数据版本+1
		dbInvestigate.setDataVersion(dbInvestigate.getDataVersion() + 1);
		dbInvestigate.setUpdateTime(now);
		daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
		// 记录用户操作
		DaikenInvestigateOperation newInvestigateOperation = new DaikenInvestigateOperation();
		newInvestigateOperation.setInvestigateId(dbInvestigate.getId());
		newInvestigateOperation.setType(dbInvestigate.getType());
		newInvestigateOperation.setUserId(daikenUser.getId());
		newInvestigateOperation.setCreateTime(now);
		newInvestigateOperation.setUpdateTime(now);
		newInvestigateOperation.setOperation(String.valueOf(dbInvestigateOptions.getId()));
		daikenInvestigateOperationDao.saveSelective(newInvestigateOperation);
		// 给用户返现
		KemeanConfig config = commonService.getConfig(DaikenConfigEnum.TOKEN_AWARD);
		Double awardToken = Double.valueOf(config.getRecord());
		daikenUser.setBalanceToken(daikenUser.getBalanceToken() + awardToken);
		daikenUser.setBalancePrice(daikenUser.getBalancePrice() + dbInvestigate.getRewardPrice());
		daikenUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(daikenUser);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
		// 客户端 生成任务流水
		commonService.createFinanceOrder(String.valueOf(dbInvestigate.getId()),
				DaikenFinanceTypeEnum.C_INVESTIGATE_VOTE.getType(), dbInvestigate.getRewardPrice(), daikenUser.getId(),
				0, dbInvestigate.getTitle(), "调研点赞收入");
		InvestigateAwardBO bo = new InvestigateAwardBO();
		bo.setAwardToken(awardToken);
		bo.setRewardPrice(dbInvestigate.getRewardPrice());
		bo.setInvestigateInfo(DaikenApiResultTips.Investigate.OPERATE_SUCCESS);
		return new KemeanResult<InvestigateAwardBO>(bo);
	}

	/**
	 * 点赞：一个用户只能操作投票问卷一次 type 1101 赞 1102 一般 1103 踩
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	@Transactional
	public KemeanResult<InvestigateAwardBO> likeInvestigateInfo(LikePO likePO, DaikenUser daikenUser)
			throws InterruptedException {
		InvestigateAwardBO bo = new InvestigateAwardBO();
		KemeanResult<InvestigateAwardBO> result = new KemeanResult<InvestigateAwardBO>(bo);
		// 一个用户只能一个调研表操作一次
		List<DaikenInvestigateOperation> dbInvestigateOperations = daikenInvestigateOperationDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "investigateId", "userId" },
				new Object[] { false, likePO.getObjId(), daikenUser.getId() });
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(likePO.getObjId());
		if (CollectionUtils.isNotEmpty(dbInvestigateOperations)) {
			return new KemeanResult<>(false, DaikenApiResultTips.Investigate.INVESTIGATE_OPERATION_REPETITION);
		}

		// 【乐观锁】 获取当前版本
		boolean flag = false;
		for (int i = 0; i < 3; i++) {
			Integer happyLock = daikenInvestigateDao.updateByHappyLock(dbInvestigate.getId(),
					dbInvestigate.getDataVersion());
			if (happyLock > 0) {
				flag = true;
				break;
			}
			Thread.sleep(1000);
			// 重新获取版本，尝试再次操作
			dbInvestigate = daikenInvestigateDao.selectById(dbInvestigate.getId());
		}
		// 数据版本已经增加，操作失败
		if (!flag) {
			return new KemeanResult<>(false, DaikenApiResultTips.Investigate.INVESTIGATE_OPERATION_REPETITION);
		}

		if (dbInvestigate.getMaxPeopleNum() != 0) {
			if (dbInvestigate.getSalesNum() >= dbInvestigate.getMaxPeopleNum()) {
				return new KemeanResult<>(false, DaikenApiResultTips.Investigate.MAX_PEOPLE_NUM);
			}
		}

		Date now = new Date();

		// 记录操作
		DaikenInvestigateOperation newInvestigateOperation = new DaikenInvestigateOperation();
		newInvestigateOperation.setInvestigateId(dbInvestigate.getId());
		newInvestigateOperation.setUserId(daikenUser.getId());
		newInvestigateOperation.setCreateTime(now);
		newInvestigateOperation.setUpdateTime(now);
		newInvestigateOperation.setType(dbInvestigate.getType());
		// 赞
		if (likePO.getType().equals(DaikenOperationTypeExplain.c_good)) {
			newInvestigateOperation.setOperation(String.valueOf(DaikenOperationTypeExplain.c_good));
			dbInvestigate.setNumGood(dbInvestigate.getNumGood() + 1);
		}
		// 一般
		if (likePO.getType().equals(DaikenOperationTypeExplain.c_common)) {
			newInvestigateOperation.setOperation(String.valueOf(DaikenOperationTypeExplain.c_common));
			dbInvestigate.setNumCommon(dbInvestigate.getNumCommon() + 1);
		}
		// 踩
		if (likePO.getType().equals(DaikenOperationTypeExplain.c_bad)) {
			newInvestigateOperation.setOperation(String.valueOf(DaikenOperationTypeExplain.c_bad));
			dbInvestigate.setNumBad(dbInvestigate.getNumBad() + 1);
		}
		// 添加领取人数
		dbInvestigate.setSalesNum(dbInvestigate.getSalesNum() + 1);
		// 数据版本+1
		dbInvestigate.setDataVersion(dbInvestigate.getDataVersion() + 1);
		dbInvestigate.setUpdateTime(new Date());
		daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
		daikenInvestigateOperationDao.saveSelective(newInvestigateOperation);
		// 一手红包 二手红包
		if (dbInvestigate.getType().equals(DaikenInvestigateTypeEnum.NEW_GOODS_LIKE.getType())
				|| dbInvestigate.getType().equals(DaikenInvestigateTypeEnum.OLD_GOODS_LIKE.getType())) {
			DaikenRedShareGet newShareGet = new DaikenRedShareGet();
			newShareGet.setToken(dbInvestigate.getToken());
			newShareGet.setUserId(daikenUser.getId());
			newShareGet.setUserNickName(daikenUser.getNickName());
			newShareGet.setMoney(dbInvestigate.getRewardPrice());
			newShareGet.setCreateTime(now);
			newShareGet.setUpdateTime(now);
			daikenRedShareGetDao.saveSelective(newShareGet);
		}
		// 给用户返现
		KemeanConfig config = commonService.getConfig(DaikenConfigEnum.TOKEN_AWARD);
		Double awardToken = Double.valueOf(config.getRecord());
		daikenUser.setBalanceToken(daikenUser.getBalanceToken() + awardToken);
		daikenUser.setBalancePrice(daikenUser.getBalancePrice() + dbInvestigate.getRewardPrice());
		daikenUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(daikenUser);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
		// 客户端 生成任务流水
		commonService.createFinanceOrder(String.valueOf(dbInvestigate.getId()),
				DaikenFinanceTypeEnum.C_INVESTIGATE_LIKE.getType(), dbInvestigate.getRewardPrice(), daikenUser.getId(),
				0, dbInvestigate.getTitle(), "调研点赞收入");
		bo.setAwardToken(awardToken);
		bo.setRewardPrice(dbInvestigate.getRewardPrice());
		bo.setInvestigateInfo(DaikenApiResultTips.Investigate.OPERATE_SUCCESS);
		return result;
	}

	/**
	 * 创建一个新订单
	 * 
	 * @author huwei
	 * @date 2018年6月22日
	 */
	private DaikenOrder createInvestigateOrder(String orderNo, Integer investigateId, DaikenUser daikenUser,
			Double pricePay, Date now) {
		DaikenOrder newOrder = new DaikenOrder();
		newOrder.setOrderNo(orderNo);
		newOrder.setIdUser(daikenUser.getId());
		newOrder.setUidUser(daikenUser.getUid());
		newOrder.setUserName(daikenUser.getNickName());
		newOrder.setIdInvestigate(investigateId);
		newOrder.setIdPurchasing(0);
		newOrder.setStatusShop(KemeanOrderEnum.OrderStatusBusiness.WAIT_PAY.getStatus());
		newOrder.setStatusUser(KemeanOrderEnum.OrderStatusUser.NEW_WIAT_PAY.getStatus());
		newOrder.setUserPhone(daikenUser.getPhone());
		newOrder.setQuantity(1);
		newOrder.setPricePay(pricePay);
		newOrder.setPriceDiscount(0.0);
		newOrder.setPriceTotal(pricePay);
		newOrder.setPostage(0.0);
		newOrder.setIsNewGoods(false);
		newOrder.setPayType(KemeanPayTypeEnum.NO_SET.getType());
		newOrder.setRecordReceiving("该订单为调研订单，没有收货地址");
		newOrder.setRecordLogistics("该订单为调研订单，没有快递公司信息");
		newOrder.setCreateYearMonth(KemeanUtilAid.formatDate(now, KemeanDateFormatEnum.YEAR_MONTH));
		newOrder.setCreateTime(now);
		newOrder.setUpdateTime(now);
		return newOrder;
	}

	/**
	 * 添加投票选手信息
	 * 
	 * @author huwei
	 * @date 2018年6月27日
	 */
	public KemeanResult<String> addInvestigatePlyear(PostVotePlyearPO postVotePlyearPO) {
		DaikenInvestigateOptions newInvestigateOptions = new DaikenInvestigateOptions();
		BeanUtils.copyProperties(postVotePlyearPO, newInvestigateOptions);
		newInvestigateOptions.setImg(DaikenUtil.parseJSONArrayByList(postVotePlyearPO.getImg()).toString());
		newInvestigateOptions.setCreateTime(new Date());
		newInvestigateOptions.setUpdateTime(new Date());
		daikenInvestigateOptionsDao.saveSelective(newInvestigateOptions);
		return new KemeanResult<>();
	}

	/**
	 * 调查问卷详情
	 * 
	 * @author huwei
	 * @date 2018年7月5日
	 */
	public QuestionnaireInfoBO questionnaireInfo(Integer objId) {
		QuestionnaireInfoBO bo = new QuestionnaireInfoBO();
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(objId);
		dbInvestigate.setNumVisit(dbInvestigate.getNumVisit() + 1);
		daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
		BeanUtils.copyProperties(dbInvestigate, bo);
		if (StringUtils.isNotBlank(dbInvestigate.getAdvertisingImg())) {
			bo.setAdvertisingImg(JSONArray.parseArray(dbInvestigate.getAdvertisingImg(), String.class));
		} else {
			bo.setAdvertisingImg(Arrays.asList());
		}
		if (StringUtils.isNotBlank(dbInvestigate.getInvestigateImg())) {
			bo.setInvestigateImg(JSONArray.parseArray(dbInvestigate.getInvestigateImg(), String.class));
		} else {
			bo.setInvestigateImg(Arrays.asList());
		}
		bo.setIsOver(false);
		Integer dateDifference = DaikenUtil.dateDifference(new Date(), dbInvestigate.getEndTime());
		if (dateDifference <= 0) {
			bo.setIsOver(true);
		}
		List<DaikenInvestigateQuestion> dbQuestions = daikenInvestigateQuestionDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "investigateId" },
				new Object[] { false, dbInvestigate.getId() });
		List<QuestionInfoBO> questionInfoBO = new ArrayList<QuestionInfoBO>(dbQuestions.size());
		if (CollectionUtils.isNotEmpty(dbQuestions)) {
			for (DaikenInvestigateQuestion daikenInvestigateQuestion : dbQuestions) {
				QuestionInfoBO infoBO = new QuestionInfoBO();
				BeanUtils.copyProperties(daikenInvestigateQuestion, infoBO);
				String question = "";
				if (daikenInvestigateQuestion.getType()
						.equals(DaikenInvestigateQuestionTypeEnum.ONE_SELECT.getType())) {
					question = "（单选题）";
				}
				if (daikenInvestigateQuestion.getType()
						.equals(DaikenInvestigateQuestionTypeEnum.MANY_SELECT.getType())) {
					question = "（多选题）";
				}
				if (daikenInvestigateQuestion.getType().equals(DaikenInvestigateQuestionTypeEnum.SIMPLE.getType())) {
					question = "（简答题）";
				}
				question += daikenInvestigateQuestion.getQuestion();
				infoBO.setQuestion(question);
				if (daikenInvestigateQuestion.getRecordAnswer() != null
						&& StringUtils.isNotBlank(daikenInvestigateQuestion.getRecordAnswer())) {
					infoBO.setRecordAnswerBO(
							JSONArray.parseArray(daikenInvestigateQuestion.getRecordAnswer(), RecordAnswerBO.class));
				} else {
					infoBO.setRecordAnswerBO(Arrays.asList());
				}
				questionInfoBO.add(infoBO);
			}
		}
		bo.setQuestionInfoBO(questionInfoBO);
		return bo;
	}

	/**
	 * 提交调查问卷
	 * 
	 * @author huwei
	 * @throws InterruptedException
	 * @date 2018年7月5日
	 */
	@Transactional
	public KemeanResult<InvestigateAwardBO> questionInvestigateInfo(QuestionInvestigateInfoPO questionInvestigateInfoPO,
			DaikenUser daikenUser) throws InterruptedException {
		Date now = new Date();
		InvestigateAwardBO bo = new InvestigateAwardBO();
		KemeanResult<InvestigateAwardBO> result = new KemeanResult<InvestigateAwardBO>(bo);
		// 一个用户只能一个调研表操作一次
		List<DaikenInvestigateOperation> dbInvestigateOperations = daikenInvestigateOperationDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "investigateId", "userId" },
				new Object[] { false, questionInvestigateInfoPO.getInvestigateInId(), daikenUser.getId() });
		if (CollectionUtils.isNotEmpty(dbInvestigateOperations)) {
			return new KemeanResult<>(false, DaikenApiResultTips.Investigate.INVESTIGATE_OPERATION_REPETITION);
		}
		DaikenInvestigate dbInvestigate = daikenInvestigateDao
				.selectById(questionInvestigateInfoPO.getInvestigateInId());
		// 【乐观锁】 获取当前版本
		boolean flag = false;
		for (int i = 0; i < 3; i++) {
			Integer happyLock = daikenInvestigateDao.updateByHappyLock(dbInvestigate.getId(),
					dbInvestigate.getDataVersion());
			if (happyLock > 0) {
				flag = true;
				break;
			}
			Thread.sleep(1000);
			// 重新获取版本，尝试再次操作
			dbInvestigate = daikenInvestigateDao.selectById(dbInvestigate.getId());
		}
		// 数据版本已经增加，操作失败
		if (!flag) {
			InvestigateAwardBO data = new InvestigateAwardBO();
			KemeanResult<InvestigateAwardBO> kemeanResult = new KemeanResult<InvestigateAwardBO>(data);
			kemeanResult.setCode(KemeanResultEnum.FAILURE.getCode());
			kemeanResult.setSuccess(false);
			bo.setInvestigateInfo(DaikenApiResultTips.Investigate.INVESTIGATE_OPERATION_REPETITION);
			bo.setAwardToken(0.0);
			bo.setRewardPrice(0.0);
			return kemeanResult;
		}
		if (dbInvestigate.getMaxPeopleNum() != 0) {
			if (dbInvestigate.getSalesNum() >= dbInvestigate.getMaxPeopleNum()) {
				return new KemeanResult<>(false, DaikenApiResultTips.Investigate.MAX_PEOPLE_NUM);
			}
		}
		// 记录操作
		List<QuestionInfoPO> questionInfoPOs = questionInvestigateInfoPO.getQuestionInfoPO();
		for (QuestionInfoPO questionInfoPO : questionInfoPOs) {
			DaikenInvestigateOperation newOperation = new DaikenInvestigateOperation();
			newOperation.setInvestigateId(dbInvestigate.getId());
			newOperation.setType(dbInvestigate.getType());
			newOperation.setUserId(daikenUser.getId());
			newOperation.setQuestionId(questionInfoPO.getQuestionId());
			newOperation.setOperation(DaikenUtil.parseJSONArrayByList(questionInfoPO.getAnswer()).toString());
			newOperation.setCreateTime(now);
			newOperation.setUpdateTime(now);
			daikenInvestigateOperationDao.saveSelective(newOperation);
		}
		dbInvestigate.setSalesNum(dbInvestigate.getSalesNum() + 1);
		// 数据版本+1
		dbInvestigate.setDataVersion(dbInvestigate.getDataVersion() + 1);
		dbInvestigate.setUpdateTime(now);
		daikenInvestigateDao.updateByPrimaryKeySelective(dbInvestigate);
		// 将问卷修改成已完成
		daikenInvestigateQuestionUserDao.updateEntityByProperties("isFinished", true,
				new String[] { "userId", "investigateId" }, new Object[] { daikenUser.getId(), dbInvestigate.getId() });
		// 给用户返现
		KemeanConfig config = commonService.getConfig(DaikenConfigEnum.TOKEN_AWARD);
		Double awardToken = Double.valueOf(config.getRecord());
		daikenUser.setBalanceToken(daikenUser.getBalanceToken() + awardToken);
		daikenUser.setBalancePrice(daikenUser.getBalancePrice() + dbInvestigate.getRewardPrice());
		daikenUser.setUpdateTime(now);
		daikenUserDao.updateByPrimaryKeySelective(daikenUser);
		kemeanRedisService.del(String.format(DaikenRedisKeyEnum.USER_CONSUMER.getKey(), daikenUser.getToken()));
		// 客户端 生成任务流水
		commonService.createFinanceOrder(String.valueOf(dbInvestigate.getId()),
				DaikenFinanceTypeEnum.C_INVESTIGATE_QUESTION.getType(), dbInvestigate.getRewardPrice(),
				daikenUser.getId(), 0, dbInvestigate.getTitle(), "调研问卷收入");

		bo.setAwardToken(awardToken);
		bo.setRewardPrice(dbInvestigate.getRewardPrice());
		bo.setInvestigateInfo(DaikenApiResultTips.Investigate.OPERATE_SUCCESS);
		return result;
	}

	/**
	 * 已结束 --- 点赞详情
	 * 
	 * @author huwei
	 * @date 2018年7月11日
	 */
	public LikeInfoOverBO likeInfoOver(Integer objId) {
		LikeInfoOverBO bo = new LikeInfoOverBO();
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(objId);
		BeanUtils.copyProperties(dbInvestigate, bo);
		bo.setTitle("【点赞】" + dbInvestigate.getTitle());
		bo.setCreateTimeStr(DaikenUtil.formatDate(dbInvestigate.getCreateTime(), "MM/dd HH:ss"));
		Double sumNum = Double
				.valueOf(dbInvestigate.getNumGood() + dbInvestigate.getNumBad() + dbInvestigate.getNumCommon());
		bo.setNumBadPercentage(0.0);
		bo.setNumGoodPercentage(0.0);
		bo.setNumCommonPercentage(0.0);
		if (sumNum != 0.0) {
			bo.setNumBadPercentage(DaikenUtil.formatDouble(dbInvestigate.getNumBad() / sumNum, 2));
			bo.setNumGoodPercentage(DaikenUtil.formatDouble(dbInvestigate.getNumGood() / sumNum, 2));
			bo.setNumCommonPercentage(DaikenUtil.formatDouble(dbInvestigate.getNumCommon() / sumNum, 2));
		}
		return bo;
	}

	/**
	 * 已结束 --- 投票详情
	 * 
	 * @author huwei
	 * @date 2018年7月12日
	 */
	public VoteInfoOverBO voteInfoOver(Integer objId) {
		VoteInfoOverBO bo = new VoteInfoOverBO();
		DaikenInvestigate dbInvestigate = daikenInvestigateDao.selectById(objId);
		BeanUtils.copyProperties(dbInvestigate, bo);
		bo.setTitle("【投票】" + dbInvestigate.getTitle());
		bo.setCreateTimeStr(DaikenUtil.formatDate(dbInvestigate.getCreateTime(), "MM/dd HH:ss"));
		// 投票总数
		Double countVotes = Double.valueOf(daikenInvestigateOptionsDao.countVotes(objId));

		List<DaikenInvestigateOptions> dbInvestigateOptions = daikenInvestigateOptionsDao.selectByProperties(
				new String[] { KemeanConstant.DATA_DELETED, "investigateId" }, new Object[] { false, objId });
		List<VoteInfoOverPlyerBO> voteInfoOverPlyerBO = new ArrayList<VoteInfoOverPlyerBO>(dbInvestigateOptions.size());
		if (CollectionUtils.isNotEmpty(dbInvestigateOptions)) {
			for (DaikenInvestigateOptions daikenInvestigateOptions : dbInvestigateOptions) {
				VoteInfoOverPlyerBO plyerBO = new VoteInfoOverPlyerBO();
				BeanUtils.copyProperties(daikenInvestigateOptions, plyerBO);
				plyerBO.setImg(JSONArray.parseArray(daikenInvestigateOptions.getImg(), String.class).get(0));
				plyerBO.setVotesPercentage(0.0);
				if (countVotes != 0.0) {
					plyerBO.setVotesPercentage(
							DaikenUtil.formatDouble(daikenInvestigateOptions.getVotes() / countVotes, 2));
				}
				voteInfoOverPlyerBO.add(plyerBO);
			}
		}
		bo.setVoteInfoOverPlyerBO(voteInfoOverPlyerBO);
		return bo;
	}
}
