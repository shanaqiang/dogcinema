package com.shana.cinema.pojo;

public class Cinema {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cinema.cid
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    private Integer cid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cinema.cname
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    private String cname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cinema.status
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cinema.description
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cinema.cid
     *
     * @return the value of t_cinema.cid
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cinema.cid
     *
     * @param cid the value for t_cinema.cid
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cinema.cname
     *
     * @return the value of t_cinema.cname
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public String getCname() {
        return cname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cinema.cname
     *
     * @param cname the value for t_cinema.cname
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cinema.status
     *
     * @return the value of t_cinema.status
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cinema.status
     *
     * @param status the value for t_cinema.status
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cinema.description
     *
     * @return the value of t_cinema.description
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cinema.description
     *
     * @param description the value for t_cinema.description
     *
     * @mbg.generated Fri Oct 18 15:15:43 CST 2019
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}