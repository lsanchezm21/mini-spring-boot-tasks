package com.example.minitasks.web;

import com.example.minitasks.dto.CreateTaskDTO;
import com.example.minitasks.model.SimpleTask;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1")
public class TaskControllerV1 {

    private final List<SimpleTask> inMemory = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(0);

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/tasks")
    public List<SimpleTask> list() {
        return inMemory;
    }

    @GetMapping("/tasks/{id}")
    public SimpleTask getById(@PathVariable Long id) {
        return inMemory.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public SimpleTask create(@RequestBody CreateTaskDTO dto) {
        SimpleTask t = new SimpleTask(seq.incrementAndGet(), dto.title(), false);
        inMemory.add(t);
        return t;
    }
}

