package com.mcb.assessment.exceptionhandler;

public class GroupNotFound extends RuntimeException{
    private String message;
    public GroupNotFound(long id){
        super();
        this.message="Group not found with id :"+ id;

    }



}