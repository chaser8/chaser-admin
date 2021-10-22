import request from '@/utils/request'

export function getRoles(data) {
  return request({
    url: '/vue-admin-template/role/getRoles',
    method: 'post',
    data
  })
}
