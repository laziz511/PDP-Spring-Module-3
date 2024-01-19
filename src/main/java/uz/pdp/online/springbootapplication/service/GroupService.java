package uz.pdp.online.springbootapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.entity.Group;
import uz.pdp.online.springbootapplication.exception.GroupNotFoundException;
import uz.pdp.online.springbootapplication.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + id));
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long id, Group updatedGroup) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group existingGroup = optionalGroup.get();
            existingGroup.setName(updatedGroup.getName());

            return groupRepository.save(existingGroup);
        } else {
            throw new GroupNotFoundException("Group not found with id: " + id);
        }
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public List<Group> getAllGroupsByName(String name) {
        return groupRepository.findAllByName(name);
    }
}
