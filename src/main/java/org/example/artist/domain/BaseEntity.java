package org.example.artist.domain;


import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 상속관계 맵핑이 아니다.  abstract 반드시 사용 .
@MappedSuperclass
public  abstract class BaseEntity {

    private String createdBy;
    private LocalDateTime createTime;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
