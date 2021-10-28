import request from '@/utils/request'

export function addFunc(data) {
  return request({
    url: '/vue-admin-template/func/',
    method: 'put',
    data
  })
}

export function delFunc(id) {
  return request({
    url: '/vue-admin-template/func/' + id,
    method: 'delete'
  })
}
export function getResources(id) {
  return request({
    url: '/vue-admin-template/func/' + id + '/resources',
    method: 'get'
  })
}
export function updateResources(data) {
  return request({
    url: '/vue-admin-template/func/resource',
    method: 'put',
    data
  })
}
