package com.nichias.model_api;

public class RegisterOTPApiResponse {
    private System system;

    private Data data;

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

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
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

    @Override
    public String toString()
    {
        return "ClassPojo [system = "+system+", data = "+data+", time = "+time+", message = "+message+", status = "+status+"]";
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


    public class Data
    {
        private String otp;

        private String mobile_number;

        private String otp_id;

        public String getOtp ()
        {
            return otp;
        }

        public void setOtp (String otp)
        {
            this.otp = otp;
        }

        public String getMobile_number ()
        {
            return mobile_number;
        }

        public void setMobile_number (String mobile_number)
        {
            this.mobile_number = mobile_number;
        }

        public String getOtp_id ()
        {
            return otp_id;
        }

        public void setOtp_id (String otp_id)
        {
            this.otp_id = otp_id;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [otp = "+otp+", mobile_number = "+mobile_number+", otp_id = "+otp_id+"]";
        }
    }


}
