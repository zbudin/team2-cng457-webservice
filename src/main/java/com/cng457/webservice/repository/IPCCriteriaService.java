package com.cng457.webservice.repository;

import java.util.List;

import com.cng457.webservice.entity.PC;

public interface IPCCriteriaService {

    List<PC> findComputersByCriteria();
}