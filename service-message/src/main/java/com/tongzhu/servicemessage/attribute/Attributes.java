package com.tongzhu.servicemessage.attribute;

import com.tongzhu.servicemessage.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
