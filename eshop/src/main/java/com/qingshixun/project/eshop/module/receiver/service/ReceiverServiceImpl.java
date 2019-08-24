package com.qingshixun.project.eshop.module.receiver.service;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.dto.ReceiverDTO;
import com.qingshixun.project.eshop.module.member.service.MemberServiceImpl;
import com.qingshixun.project.eshop.module.receiver.dao.ReceiverDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReceiverServiceImpl extends BaseService {

    @Autowired
    private ReceiverDaoMyBatis receiverDao;

    @Autowired
    private MemberServiceImpl memberService;

    /**
     * 获取用户收货人列表
     */
    public List<ReceiverDTO> getReceiversByMember(Long memberId) {
        return receiverDao.getReceiversByMember(memberId);
    }

    /**
     * 获取收货人信息
     */
    public ReceiverDTO getReceiver(Long receiverId) {
        return receiverDao.getReceiver(receiverId);
    }

    /**
     * 保存收货地址
     */
    public void saveOrUpdateReceiver(ReceiverDTO receiver, MemberDTO member) {
        receiver.setMember(member);

        receiverDao.saveOrUpdateReceiver(receiver);

        memberService.updateMemberDefaultReceiverId(member.getId(), receiver.getId());
    }

    /**
     * 删除收货人信息
     */
    public void deleteReceiver(MemberDTO member, Long receiverId) {
        // 获取收货人信息
        ReceiverDTO receiver = getReceiver(receiverId);

        // 删除指定收货人
        receiverDao.deleteReceiver(receiverId);

        // 判断待删除地址是否为默认地址
        if (receiver.getId().longValue() == member.getDefaultReceiverId().longValue()) {
            // 获取收货人列表
            List<ReceiverDTO> receivers = getReceiversByMember(member.getId());

            if (!receivers.isEmpty()) {
                // 将最近一个收货人地址更新为用户的默认地址
                memberService.updateMemberDefaultReceiverId(member.getId(), receivers.get(0).getId());
            }
        }

    }

}
