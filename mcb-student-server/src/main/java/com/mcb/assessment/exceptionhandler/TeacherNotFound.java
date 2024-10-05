package com.mcb.assessment.exceptionhandler;

public class TeacherNotFound extends RuntimeException{
    private String message;
    public TeacherNotFound(long id){
        super();
        this.message="Teacher not found with id :"+ id;

    }



}
