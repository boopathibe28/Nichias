package com.nichias.model_api;

public class OrderViewSimApiResponse {
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
        private String sim_order_key;

        private String proof_image_path;

        private String id_number;

        private String gender;

        private String expiry_date;

        private String middle_name;

        private String po_box;

        private String contact_number;

        private String sim_order_number;

        private String email_address;

        private String sim_plan;

        private String dob;

        private String first_name;

        private String family_name;

        public String getSim_order_key ()
        {
            return sim_order_key;
        }

        public void setSim_order_key (String sim_order_key)
        {
            this.sim_order_key = sim_order_key;
        }

        public String getProof_image_path ()
        {
            return proof_image_path;
        }

        public void setProof_image_path (String proof_image_path)
        {
            this.proof_image_path = proof_image_path;
        }

        public String getId_number ()
        {
            return id_number;
        }

        public void setId_number (String id_number)
        {
            this.id_number = id_number;
        }

        public String getGender ()
        {
            return gender;
        }

        public void setGender (String gender)
        {
            this.gender = gender;
        }

        public String getExpiry_date ()
        {
            return expiry_date;
        }

        public void setExpiry_date (String expiry_date)
        {
            this.expiry_date = expiry_date;
        }

        public String getMiddle_name ()
        {
            return middle_name;
        }

        public void setMiddle_name (String middle_name)
        {
            this.middle_name = middle_name;
        }

        public String getPo_box ()
        {
            return po_box;
        }

        public void setPo_box (String po_box)
        {
            this.po_box = po_box;
        }

        public String getContact_number ()
        {
            return contact_number;
        }

        public void setContact_number (String contact_number)
        {
            this.contact_number = contact_number;
        }

        public String getSim_order_number ()
        {
            return sim_order_number;
        }

        public void setSim_order_number (String sim_order_number)
        {
            this.sim_order_number = sim_order_number;
        }

        public String getEmail_address ()
        {
            return email_address;
        }

        public void setEmail_address (String email_address)
        {
            this.email_address = email_address;
        }

        public String getSim_plan ()
        {
            return sim_plan;
        }

        public void setSim_plan (String sim_plan)
        {
            this.sim_plan = sim_plan;
        }

        public String getDob ()
        {
            return dob;
        }

        public void setDob (String dob)
        {
            this.dob = dob;
        }

        public String getFirst_name ()
        {
            return first_name;
        }

        public void setFirst_name (String first_name)
        {
            this.first_name = first_name;
        }

        public String getFamily_name ()
        {
            return family_name;
        }

        public void setFamily_name (String family_name)
        {
            this.family_name = family_name;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [sim_order_key = "+sim_order_key+", proof_image_path = "+proof_image_path+", id_number = "+id_number+", gender = "+gender+", expiry_date = "+expiry_date+", middle_name = "+middle_name+", po_box = "+po_box+", contact_number = "+contact_number+", sim_order_number = "+sim_order_number+", email_address = "+email_address+", sim_plan = "+sim_plan+", dob = "+dob+", first_name = "+first_name+", family_name = "+family_name+"]";
        }
    }


}
