import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/vue-admin-template/login',
    method: 'post',
    data
  })
}

export function getInfo(data) {
  return request({
    url: '/vue-admin-template/uaa/user',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/logout',
    method: 'post'
  })
}
export function page(data) {
  return request({
    url: '/vue-admin-template/user/page',
    method: 'post',
    data
  })
}
export function create(data) {
  return request({
    url: '/vue-admin-template/user/add',
    method: 'post',
    data
  })
}
export function usernameUniqueCheck(data) {
  return request({
    url: '/vue-admin-template/user/usernameCheck',
    method: 'post',
    data
  })
}
export function update(data) {
  return request({
    url: '/vue-admin-template/user',
    method: 'patch',
    data
  })
}
export function getUserRoles(data) {
  return request({
    url: '/vue-admin-template/user/getUserRoles',
    method: 'post',
    data
  })
}
export function updateUserRoles(data) {
  return request({
    url: '/vue-admin-template/user/updateUserRoles',
    method: 'post',
    data
  })
}

export function freeze(data) {
  return request({
    url: '/vue-admin-template/user/freeze',
    method: 'post',
    data
  })
}

export function unfreeze(data) {
  return request({
    url: '/vue-admin-template/user/unfreeze',
    method: 'post',
    data
  })
}
