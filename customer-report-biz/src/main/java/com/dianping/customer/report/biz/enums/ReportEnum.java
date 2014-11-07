package com.dianping.customer.report.biz.enums;

public enum ReportEnum {
    CompetitiveIntelligenceByCategoryMonth(1, "CompetitiveIntelligenceByCategoryMonth"),
    CompetitiveIntelligenceByCategoryWeek(2, "CompetitiveIntelligenceByCategoryWeek"),
    CompetitiveIntelligenceByRegionMonth(3, "CompetitiveIntelligenceByRegionMonth"),
    CompetitiveIntelligenceByRegionWeek(4, "CompetitiveIntelligenceByRegionWeek"),
    Category(5, "Category"),
    KeyShopQty(6, "KeyShopQty"),
    KeyShopSummaryOfDp(7, "KeyShopSummaryOfDp"),
    KeyShopDetail(8, "KeyShopDetail"),
    CompetitiveIntelligenceByShopMonth(9, "CompetitiveIntelligenceByShopMonth"),
    KeyLocalShopDetail(10, "KeyLocalShopDetail"),
    KeyShopSaleDetailsByStatus(11, "KeyShopSaleDetailsByStatus"),
    KpiByMonth(12, "KpiByMonth"),
    KpiByWeek(13, "KpiByWeek"),
    KpiLeafByMonth(14, "KpiLeafByMonth"),
    KpiLeafByWeek(15, "KpiLeafByWeek"),

    VisitByCityWeek(16, "VisitByCityWeek"),
    VisitByCityMonth(17, "VisitByCityMonth"),
    VisitByGroupWeek(18, "VisitByGroupWeek"),
    VisitByGroupMonth(19, "VisitByGroupMonth"),
    VisitByUserWeek(20, "VisitByUserWeek"),
    VisitByUserMonth(21, "VisitByUserMonth"),
    VisitDetailByMonth(22, "VisitDetailByMonth"),
    VisitDetailByWeek(23, "VisitDetailByWeek");


    private int typeId;
    private String name;

    ReportEnum(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }
}
