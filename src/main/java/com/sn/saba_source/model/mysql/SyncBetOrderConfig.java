package com.sn.saba_source.model.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sync_betorder_config")
@Data
public class SyncBetOrderConfig {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "provider_id")
    private Integer providerId;

    @Column(name= "platform_id")
    private Integer platformId;

    @Column(name = "agcode", length = 50)
    private String agcode;

    @Column(name = "general_agent", length = 32)
    private String generalAgent;

    @Column(name = "agent", nullable = false, length = 32)
    private String agent;

    @Column(name = "enabled", nullable = false)
    private Integer enabled;

    @Column(name = "call_url", nullable = false, length = 100)
    private String callUrl;

    @Column(name = "error_msg", length = 100)
    private String errormsg;

    @Column(name = "game_provider", nullable = false, length = 16)
    private String gameProvider;

    @Column(name = "has_keno")
    private Integer hasKeno;

    @Column(name = "has_live")
    private Integer hasLive;

    @Column(name = "has_3d")
    private Integer has3d;

    @Column(name = "has_slot")
    private Integer hasSlot;

    @Column(name = "has_sports")
    private Integer hasSports;

    @Column(name = "last_sync_backward_time")
    private Date lastSyncBackwardTime;

    @Column(name = "last_sync_forward_time")
    private Date lastSyncForwardTime;

    @Column(name = "security_key")
    private String securityKey;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "cookie", length = 2000)
    private String cookie;

    @Column(name = "querystring", length = 250)
    private String queryString;
}
