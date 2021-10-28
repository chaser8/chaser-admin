import request from '@/utils/request'

export function delMenu(id) {
  return request({
    url: '/vue-admin-template/menu/' + id,
    method: 'delete'
  })
}

export function getLevelMenus() {
  const data = {}
  return request({
    url: '/vue-admin-template/menu/getLevelMenus',
    method: 'post',
    data
  })
}
export function getChildren(id) {
  return request({
    url: '/vue-admin-template/menu/getChildren/' + id,
    method: 'get'
  })
}

export function getMenuFunc(id) {
  return request({
    url: '/vue-admin-template/menu/getMenuFunc/' + id,
    method: 'get'
  })
}

export function addMenu(data) {
  return request({
    url: '/vue-admin-template/menu/',
    method: 'put',
    data
  })
}
