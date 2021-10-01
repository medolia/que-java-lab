package com.medolia.lab.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author lbli
 * @date 2021/9/14
 */
@Service
public class SequenceService {

    private SequenceDao sequenceDao;

    @Autowired(required = false)
    public void setSequenceDao(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }


}
