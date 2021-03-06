package rocketmq;

import com.alibaba.rocketmq.common.message.Message;
import com.tongbanjie.tarzan.client.ClientConfig;
import com.tongbanjie.tarzan.client.MessageResult;
import com.tongbanjie.tarzan.client.transaction.TransactionCheckListener;
import com.tongbanjie.tarzan.common.Constants;
import com.tongbanjie.tarzan.rocketmq.RocketMQMessageNotifier;
import org.junit.Before;
import org.junit.Test;

/**
 * RocketMQ Client Test <p>
 * 〈功能详细描述〉
 *
 * @author zixiao
 * @date 16/10/27
 */
public class RocketMQClientTest {

    private ClientConfig clientConfig;

    @Before
    public void before() throws Exception {
        clientConfig = new ClientConfig("zk.tbj.com:2181");
    }

    @Test
    public void sendMessage() throws Exception {

        RocketMQMessageNotifier mqNotifyManager = create();

        for(int i=0; i< 10; i++) {
            Message message = new Message();
            message.setTopic(Constants.TARZAN_TEST_TOPIC);
            message.setKeys("cluster_msg_" + i);
            message.setBody(("RocketMQTest " + i).getBytes());

            MessageResult result = mqNotifyManager.sendMessage(message);
            if (result.isSuccess()) {
                System.out.println(">>>Send message '" + message.getKeys() + "' to server success. msgId=" + result.getMsgId());
            } else {
                System.err.println(">>>Send message '" + message.getKeys() + "' to server failed, " + result.getErrorMsg());
            }
        }

        Thread.sleep(5000<<10);

    }

    private RocketMQMessageNotifier create() throws Exception {
        TransactionCheckListener checkListener = new TestTransactionCheckListener();
        RocketMQMessageNotifier mqNotifyManager = new RocketMQMessageNotifier(Constants.TARZAN_TEST_P_GROUP, Constants.TARZAN_TEST_TOPIC, checkListener, clientConfig);
        mqNotifyManager.init();
        return mqNotifyManager;
    }


}
