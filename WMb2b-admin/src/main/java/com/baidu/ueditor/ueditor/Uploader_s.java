package com.baidu.ueditor.ueditor;

import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.Base64Uploader;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
public class Uploader_s
{
  private HttpServletRequest request = null;
  private Map<String, Object> conf = null;

  public Uploader_s(HttpServletRequest request, Map<String, Object> conf) {
    this.request = request;
    this.conf = conf;
  }

  public final State doExec() {
    String filedName = (String)this.conf.get("fieldName");
    State state = null;

    if ("true".equals(this.conf.get("isBase64")))
      state = Base64Uploader.save(this.request.getParameter(filedName), 
        this.conf);
    else {
      state = BinaryUploader_s.save(this.request, this.conf);
    }

    return state;
  }
}