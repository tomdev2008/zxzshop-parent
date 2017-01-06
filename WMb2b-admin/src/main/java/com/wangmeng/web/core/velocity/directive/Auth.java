package com.wangmeng.web.core.velocity.directive;

import com.wangmeng.spring.ApplicationContextHolderSingleton;
import com.wangmeng.sys.authority.api.IAuthorityService;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * <p> 自定义权限指令 </p>
 *
 * @author changming.Y <changming.yang.ah@gmail.com>
 * @since 2016-12-21 15:04
 */
public class Auth extends Directive {

    @Override
    public String getName() {
        return "auth";
    }

    @Override
    public int getType() {
        return BLOCK;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {

        SimpleNode userIdNode = (SimpleNode) node.jjtGetChild(0);
        Long userId = new Long(0);
        if (userIdNode!=null && userIdNode.value(context)!=null){
            userId = (Long)userIdNode.value(context);
        }

        SimpleNode resourceNode = (SimpleNode)node.jjtGetChild(1);
        String resourceName=null;
        if (resourceNode!=null && resourceNode.value(context)!=null){
            resourceName = (String)resourceNode.value(context);
        }

        Node body = node.jjtGetChild(2);
        StringWriter sw = new StringWriter();
        body.render(context,sw);
        String bodyContext = sw.toString();

        if (userId>0 && resourceName!=null && !"".equals(resourceName)) {

            IAuthorityService authorityService = (IAuthorityService) ApplicationContextHolderSingleton.getInstance().getBean("authorityService");

            if (authorityService.hasPrivilege(userId.intValue(),resourceName)){
                writer.write(bodyContext);
                return true;
            }
        }
        return false;
    }
}
