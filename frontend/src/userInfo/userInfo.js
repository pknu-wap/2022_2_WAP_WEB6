import React from 'react';

export const getToken = () => {
    return localStorage.getItem('token');
}

export const getUsername = () => {
    return localStorage.getItem("username");
};

export const getId = () => {
    return localStorage.getItem("id")
}