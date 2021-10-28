import request from '@/utils/request'

export function getAll() {
  return request({
    url: '/vue-admin-template/resource',
    method: 'get'
  })
}
