import request from '@/utils/request'

export function getAll() {
  return request({
    url: '/vue-admin-template/resource',
    method: 'get'
  })
}
export function page(data) {
  return request({
    url: '/vue-admin-template/resource',
    method: 'post',
    data
  })
}
export function deleteResource(id) {
  return request({
    url: '/vue-admin-template/resource/' + id,
    method: 'delete'
  })
}
export function merge(data) {
  return request({
    url: '/vue-admin-template/resource',
    method: 'put',
    data
  })
}
