package com.chejconsultor.chej_androidnativeradiobutton;

public class Response {

    public boolean IsSucces;

    public String Message;

    public Object Result;

    public Response(
            boolean _isSucces,
            String _message,
            Object _result)
    {
        this.IsSucces = _isSucces;
        this.Message = _message;
        this.Result = _result;
    }
}