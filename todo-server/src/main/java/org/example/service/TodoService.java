package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor

    public class TodoService {

    private final TodoRepository todoRepository;

    // todo 리스트 목록에 아이템을 추가
    // todo 리스트 목록중 특정 아이템을 조회
    // todo 리스트 전체 목록을 조회
    // todo 리스트 목록중 특정 아이템을 수정
    // todo 리스트 목록중 특정 아이템을 삭제
    // todo 리스트 전체 목록을 삭제


    public TodoModel add (TodoRequest todoRequest){
        TodoModel todoEntity = new TodoModel();
        todoEntity.setTitle(todoRequest.getTitle());
        todoEntity.setOrder(todoRequest.getOrder());
        todoEntity.setCompletesd(todoRequest.getCompleted());

        TodoModel entity = this.todoRepository.save(todoEntity);

        return entity;
    }

    public TodoModel searchById (Long id){

      return  this.todoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        }

    public List<TodoModel> clearAll(){
        return this.todoRepository.findAll();
    }


    public TodoModel updateById(Long id, TodoRequest request){
        TodoModel todoEntity = this.searchById(id);

        if(request.getTitle() != null){
            todoEntity.setTitle(request.getTitle());
        }

        if(request.getOrder() != null){
            todoEntity.setOrder(request.getOrder());
        }

        if(request.getCompleted() != null){
            todoEntity.setCompletesd(request.getCompleted());
        }

        return this.todoRepository.save(todoEntity);
    }

    public void delelteById(Long id){

        this.todoRepository.deleteById(id);


    }

    public void deleteAll(){

        this.todoRepository.deleteAll();

    }


    public List<TodoModel> searchAll() {
        return this.todoRepository.findAll();
    }
}
