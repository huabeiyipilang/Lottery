package cn.kli.lottery;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrementPrimaryKey;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;
import android.content.Intent;
import android.graphics.Bitmap;

@Table("lottery_log")
public class LotteryLog extends Model {
    @AutoIncrementPrimaryKey
    @Column("id")
    public long id;
    
    @Column("title")
    public String title;
    
    @Column("icon")
    public Bitmap icon;
    
    @Column("package_name")
    public String packageName;
    
    @Column("intent")
    public Intent intent;
    
    @Column("count")
    public long count;
    
    @Column("keyword_quanpin")
    public String keyword_quanpin;
    
    @Column("keyword_quanpin_t9")
    public String keyword_quanpin_t9;

    @Column("keyword_shouzimu")
    public String keyword_shouzimu;

    @Column("keyword_shouzimu_t9")
    public String keyword_shouzimu_t9;
    
}
