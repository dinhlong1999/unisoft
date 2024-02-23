package com.example.projectintern.dto.orderDetail;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "analystRequest",propOrder = {
        "dateStart","dateEnd","limit","page"
})
public class AnalystRequest {
    @XmlElement
    private String dateStart;
    @XmlElement
    private String dateEnd;
    @XmlElement
    private int limit;
    @XmlElement
    private int page;

    public AnalystRequest() {
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
