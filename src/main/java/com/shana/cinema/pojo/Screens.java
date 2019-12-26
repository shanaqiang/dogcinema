package com.shana.cinema.pojo;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/21
 * @since 1.0.0
 */
public class Screens {
    private int sid;
    private String sname;
    private int cbid;
    private int seatcount;
    private int seatx;
    private int seaty;
    private int corridory;
    private String category;
    private int status;
    private String description;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getCbid() {
        return cbid;
    }

    public void setCbid(int cbid) {
        this.cbid = cbid;
    }

    public int getSeatcount() {
        return seatcount;
    }

    public void setSeatcount(int seatcount) {
        this.seatcount = seatcount;
    }

    public int getSeatx() {
        return seatx;
    }

    public void setSeatx(int seatx) {
        this.seatx = seatx;
    }

    public int getSeaty() {
        return seaty;
    }

    public void setSeaty(int seaty) {
        this.seaty = seaty;
    }

    public int getCorridory() {
        return corridory;
    }

    public void setCorridory(int corridory) {
        this.corridory = corridory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Screens{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", cbid='" + cbid + '\'' +
                ", seatcount=" + seatcount +
                ", seatx=" + seatx +
                ", seaty=" + seaty +
                ", corridory=" + corridory +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}

