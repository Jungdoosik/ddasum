package kr.or.ddasum.bizMember.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddasum.bizMember.model.dao.BizMemberDAO;
import kr.or.ddasum.bizMember.model.vo.BizGoods;
import kr.or.ddasum.member.model.vo.BizMember;


@Service
public class BizMemberServiceImpl implements BizMemberService{

	@Autowired
	private BizMemberDAO bDAO;

	@Override
	public BizMember bizManage(BizMember bizMember) {
		return bDAO.bizManage(bizMember);
	}

	@Override
	public ArrayList<BizGoods> goodsManage(int bizNo) {
		return bDAO.goodsManage(bizNo);
	}

	@Override
	public int updateBiz(BizMember bz) {
		return bDAO.updateBiz(bz);
		
	}

	

}



