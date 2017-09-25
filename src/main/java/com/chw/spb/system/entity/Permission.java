package com.chw.spb.system.entity;

public class Permission {
    private Integer id;

    private Integer pid;

    private String name;

    private String type;

    private Integer sort;

    private String url;

    private String permCode;

    private String icon;

    private String state;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode == null ? null : permCode.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;

        Permission that = (Permission) o;
        if (getId() == that.getId() && getPid() == that.getPid()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPid() != null ? getPid().hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "Permission [id=" + id + ", pid=" + pid + ", name=" + name + ", type=" + type + ", sort=" + sort
				+ ", url=" + url + ", permCode=" + permCode + ", icon=" + icon + ", state=" + state + ", description="
				+ description + "]";
	}



}