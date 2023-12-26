package uz.pdp.online.springbootapplication.listener;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

@Slf4j
public class EntityModificationListener {

    @PostPersist
    public void onPostPersist(Object entity) {
        log.info("Entity created: {}", entity.toString());
    }

    @PostUpdate
    public void onPostUpdate(Object entity) {
        log.info("Entity updated: {}", entity.toString());
    }

    @PostRemove
    public void onPostRemove(Object entity) {
        log.info("Entity deleted: {}", entity.toString());
    }
}
