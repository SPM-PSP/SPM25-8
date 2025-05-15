import { shallowMount } from '@vue/test-utils'
import Banner from '@/components/Banner.vue'

jest.useFakeTimers()

describe('Banner.vue', () => {
  const mockData = [
    { id: 1, name: 'Banner 1', cover: 'cover1.jpg' },
    { id: 2, name: 'Banner 2', cover: 'cover2.jpg' },
    { id: 3, name: 'Banner 3', cover: 'cover3.jpg' }
  ]

  it('renders with default props', () => {
    const wrapper = shallowMount(Banner, {
      propsData: {
        data: mockData
      }
    })
    
    expect(wrapper.exists()).toBe(true)
    expect(wrapper.find('img').exists()).toBe(true)
    expect(wrapper.find('.tip-name').exists()).toBe(true)
  })
  
  it('renders with custom props', () => {
    const wrapper = shallowMount(Banner, {
      propsData: {
        data: mockData,
        width: '300px',
        height: '200px',
        borderRadius: '10px',
        time: 5000
      }
    })
    
    const imgElement = wrapper.find('img')
    expect(imgElement.attributes('style')).toContain('width: 300px')
    expect(imgElement.attributes('style')).toContain('height: 200px')
    expect(imgElement.attributes('style')).toContain('border-radius: 10px')
  })
  
  it('initializes with first data item', () => {
    const wrapper = shallowMount(Banner, {
      propsData: {
        data: mockData
      }
    })
    
    expect(wrapper.vm.activeData).toEqual(mockData[0])
    expect(wrapper.vm.index).toBe(0)
  })
  
  it('emits on-click event when clicking tip-name', async () => {
    const wrapper = shallowMount(Banner, {
      propsData: {
        data: mockData
      }
    })
    
    await wrapper.find('.tip-name').trigger('click')
    expect(wrapper.emitted('on-click')).toBeTruthy()
    expect(wrapper.emitted('on-click')[0][0]).toEqual(mockData[0])
  })
  
  it('calls config method on mount', () => {
    const configSpy = jest.spyOn(Banner.methods, 'config')
    const wrapper = shallowMount(Banner, {
      propsData: {
        data: mockData
      }
    })
    
    expect(configSpy).toHaveBeenCalled()
    configSpy.mockRestore()
  })
  
  it('resets configuration when data changes', async () => {
    const wrapper = shallowMount(Banner, {
      propsData: {
        data: mockData
      }
    })
    
    const configSpy = jest.spyOn(wrapper.vm, 'config')
    
    const newData = [
      { id: 4, name: 'New Banner 1', cover: 'new1.jpg' },
      { id: 5, name: 'New Banner 2', cover: 'new2.jpg' }
    ]
    
    await wrapper.setProps({ data: newData })
    
    expect(configSpy).toHaveBeenCalled()
    expect(wrapper.vm.activeData).toEqual(newData[0])
  })
}) 