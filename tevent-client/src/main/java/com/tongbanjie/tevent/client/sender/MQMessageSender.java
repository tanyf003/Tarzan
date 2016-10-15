package com.tongbanjie.tevent.client.sender;

import com.tongbanjie.tevent.client.ClientController;
import com.tongbanjie.tevent.common.body.CustomBody;
import com.tongbanjie.tevent.rpc.protocol.header.CheckTransactionStateHeader;

/**
 * 〈一句话功能简述〉<p>
 * 〈功能详细描述〉
 *
 * @author zixiao
 * @date 16/10/13
 */
public interface MQMessageSender<T extends CustomBody> {

    int checkThreadPoolCoreSize = 1;

    int checkThreadPoolMaxSize = 1;

    int checkRequestHoldMax = 2000;

    TransactionCheckListener transactionCheckListener();

    void checkTransactionState(final String addr, final T mqBody,
                               final CheckTransactionStateHeader checkTransactionStateHeader,
                               final ClientController clientController);


}