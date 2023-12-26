package uz.pdp.online.springbootapplication.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

public class EntityModificationListener {

    private static final Logger logger = LoggerFactory.getLogger(EntityModificationListener.class);

    @PostPersist
    public void onPostPersist(Object entity) {
        logger.info("Entity created: {}", entity.toString());
    }

    @PostUpdate
    public void onPostUpdate(Object entity) {
        logger.info("Entity updated: {}", entity.toString());
    }

    @PostRemove
    public void onPostRemove(Object entity) {
        logger.info("Entity deleted: {}", entity.toString());
    }
}
