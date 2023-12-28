package com.nttdata.bootcamp.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
public class OrderWorkflow implements Workflow {

  private final List<WorkflowStep> steps;

  public OrderWorkflow(List<WorkflowStep> steps) {
    this.steps = steps;
  }

  @Override
  public List<WorkflowStep> getSteps() {
    return steps;
  }
}
