package com.kemean.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.kemean.bean.DaikenUser;
import com.kemean.constant.KemeanConstant;
import com.kemean.dao.DaikenUserDao;
import com.kemean.dao.su.DaoSupport;
import com.kemean.mapper.DaikenUserMapper;
import com.kemean.vo.bo.admin.AdminChartBO;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Repository
public class DaikenUserDaoImpl extends DaoSupport<DaikenUser> implements DaikenUserDao {

	@Autowired
	private DaikenUserMapper mapper;

	@Override
	protected Mapper<DaikenUser> getMapper() {
		return mapper;
	}

	@Override
	public DaikenUser selectByToken(String userToken, List<Integer> asList) {
		Example example = new Example(DaikenUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("token", userToken);
		criteria.andIn("userType", asList);
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		List<DaikenUser> result = mapper.selectByExample(example);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public DaikenUser selectByPhone(String phone, List<Integer> userType) {
		Example example = new Example(DaikenUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("phone", phone);
		criteria.andIn("userType", userType);
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		List<DaikenUser> result = mapper.selectByExample(example);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public Integer selectTheMaxId() {
		return mapper.selectTheMaxId();
	}

	@Override
	public Integer selectCountAge(Integer minAge, Integer maxAge) {
		Example example = new Example(DaikenUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andBetween("age", minAge, maxAge);
		return mapper.selectCountByExample(example);
	}

	@Override
	public List<DaikenUser> selectPutUser(List<Integer> userJobId, Boolean userSex, Integer pageNo, Integer pageSize) {
		Example example = new Example(DaikenUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		criteria.andIn("profession", userJobId);
		if (userSex != null) {
			criteria.andEqualTo("sexMan", userSex);
		}
		PageHelper.startPage(pageNo, pageSize);
		return mapper.selectByExample(example);
	}

	@Override
	public Double countToken() {
		Double countToken = 0.0;
		Example example = new Example(DaikenUser.class);
		List<DaikenUser> dbUsers = mapper.selectByExample(example);
		for (DaikenUser daikenUser : dbUsers) {
			countToken += daikenUser.getBalanceToken();
		}
		return countToken;
	}

	@Override
	public List<DaikenUser> selectGoodsPutUser(Integer userId, List<Integer> cityId, List<String> ages,
			List<Integer> userSex) {
		Example example = new Example(DaikenUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(KemeanConstant.DATA_DELETED, false);
		if (!cityId.isEmpty()) {
			criteria.andIn("cityId", cityId);
		}
		if (userId != null) {
			criteria.andEqualTo("id", userId);
		}
		if (!userSex.isEmpty()) {
			criteria.andIn("sexMan", userSex);
		}

		Boolean flag = true;
		if (!ages.isEmpty()) {

			for (String item : ages) {
				String[] split = item.split(",");
				if (flag) {
					criteria.andBetween("age", split[0], split[1]);
					flag = false;
				} else {
					criteria.orBetween("age", split[0], split[1]);
				}
			}

		}
		return mapper.selectByExample(example);
	}

	@Override
	public List<AdminChartBO> selectUserToken(Integer userType, String dateStart, String dateEnd, Integer limit) {
		return mapper.selectUserToken(userType, dateStart, dateEnd, limit);
	}

}
