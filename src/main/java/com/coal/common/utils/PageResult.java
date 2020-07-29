package com.coal.common.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private Long total;
    private Long totalPage;
    private int page;
    private int rows;
    private List<T> list;

    public PageResult() {
    }

    public PageResult(Long total, int page, int realRows, List<T> items, int rows) {
        //realRows为当前页应该有的数据条数，分页为5条每页，但是最后一页条数可能为3，realRows=3；
        this.total = total;
        this.list = items;
        if(page<=0) page=1;
        this.page = page;
        if (rows <= 0) rows = 5;
        this.rows = realRows;
        if (total % rows == 0)
            this.totalPage = total / rows;
        else
            this.totalPage = total / rows+1;
    }


}
