package per.poacher.pojo;

import java.util.List;

/**
 * @author poacher
 * @create 2021-05-03-8:33
 */
public class Page<T> {

    public static final int PAGE_SIZE =  4;

    private int pageNo;     //当前页码
    private int pageTotal;      //总页码
    private int pageSize = PAGE_SIZE;   //当前页显示数量
    private int pageTotalCount; //总记录数
    private String url;
    private List<T> items;   //当前页数据

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if(pageNo < 1) {
            this.pageNo = 1;
            return;
        }
        if(pageNo > pageTotal) {
            this.pageNo = pageTotal;
            return;
        }
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", url='" + url + '\'' +
                ", items=" + items +
                '}';
    }
}