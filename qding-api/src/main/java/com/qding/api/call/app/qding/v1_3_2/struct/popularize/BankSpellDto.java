package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/28.
 */
public class BankSpellDto implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 7549722204696923501L;

	private String spell;

    private List<BankDto> list;


    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public List<BankDto> getList() {
        return list;
    }

    public void setList(List<BankDto> list) {
        this.list = list;
    }

    public BankSpellDto(String spell, List<BankDto> list) {
        this.spell = spell;
        this.list = list;
    }
}
