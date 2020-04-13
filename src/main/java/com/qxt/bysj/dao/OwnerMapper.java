package com.qxt.bysj.dao;

import com.qxt.bysj.domain.Owner;

public interface OwnerMapper extends BaseMapper<Owner> {
    Owner selectByOwnerId(Integer ownerId);
}