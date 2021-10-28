import request from '@/utils/request'

export function getRoles(data) {
  return request({
    url: '/vue-admin-template/role/getRoles',
    method: 'post',
    data
  })
}
export function getRolePage(data) {
  return request({
    url: '/vue-admin-template/role/getRolePage',
    method: 'post',
    data
  })
}

export function merge(data) {
  return request({
    url: '/vue-admin-template/role/merge',
    method: 'post',
    data
  })
}
export function del(data) {
  return request({
    url: '/vue-admin-template/role',
    method: 'delete',
    data
  })
}
export function getRoleMenus(data) {
  return request({
    url: '/vue-admin-template/role/getRoleMenus',
    method: 'post',
    data
  })
}
export function updateRoleMenus(data) {
  return request({
    url: '/vue-admin-template/role/updateRoleMenus',
    method: 'post',
    data
  })
}
