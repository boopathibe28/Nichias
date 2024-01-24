package com.nichias.model_api;

public class NewsletterCreateApiResponse {
    private System system;

    private Object data;

    private String time;

    private String message;

    private String status;

    public System getSystem ()
    {
        return system;
    }

    public void setSystem (System system)
    {
        this.system = system;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }


    public class System
    {
        private String nc;

        public String getNc ()
        {
            return nc;
        }

        public void setNc (String nc)
        {
            this.nc = nc;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [nc = "+nc+"]";
        }
    }



}
