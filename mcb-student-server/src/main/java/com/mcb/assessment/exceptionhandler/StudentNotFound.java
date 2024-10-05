package com.mcb.assessment.exceptionhandler;

public class StudentNotFound extends RuntimeException{
    private String message;
    public StudentNotFound(long id){
        super();
        this.message="Student not found with id :"+ id;

    }



}
