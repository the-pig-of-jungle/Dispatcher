package com.liinji.ppgdeliver.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liinji.ppgdeliver.R;
import com.liinji.ppgdeliver.bean.ProblemOrderItem;

import java.util.List;

/**
 * Created by 朱志强 on 2017/6/16.
 */

public class ProblemOrderItemAdapter extends BaseQuickAdapter<ProblemOrderItem> {
    public ProblemOrderItemAdapter(List<ProblemOrderItem> data) {
        super(R.layout.problem_order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ProblemOrderItem problemOrderItem) {
        baseViewHolder.setText(R.id.problem_order,"运单号：" + problemOrderItem.getWaybillNo())
                .setText(R.id.register_pos,"登记网点：" + problemOrderItem.getRegisterBranchName())
                .setText(R.id.receive_pos,"接收网点：" + problemOrderItem.getReceiveBranchName())
                .setText(R.id.problem_state,"问题状态：" + problemOrderItem.getProblemStatus())
                .setText(R.id.register_person,"登记人：" + problemOrderItem.getRegisterUserName())
                .setText(R.id.register_time,"登记时间：" + problemOrderItem.getRegisterTime())
                .setImageResource(R.id.process_state,problemOrderItem.getReplyStatusRemark().equals("处理中") ? R.drawable.wtjl_handling : R.drawable.wtjl_finished);

    }
}
