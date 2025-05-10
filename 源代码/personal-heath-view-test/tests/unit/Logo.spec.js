import { shallowMount, createLocalVue } from '@vue/test-utils'
import Logo from '@/components/Logo.vue'

const localVue = createLocalVue()
// mock element-ui 组件
localVue.component('el-image', { 
  template: '<img :src="src" :fit="fit" />',
  props: ['src', 'fit'] 
})

describe('Logo.vue', () => {
  it('renders with default props', () => {
    const wrapper = shallowMount(Logo, {
      localVue
    })
    
    expect(wrapper.exists()).toBe(true)
    expect(wrapper.find('.logo').exists()).toBe(true)
    expect(wrapper.find('div').exists()).toBe(true)
    expect(wrapper.text()).toContain('康乐智助')
  })
  
  it('renders with flag=true (hides text)', () => {
    const wrapper = shallowMount(Logo, {
      localVue,
      propsData: {
        flag: true
      }
    })
    
    expect(wrapper.find('.logo').exists()).toBe(true)
    expect(wrapper.find('div').exists()).toBe(false)
    expect(wrapper.text()).not.toContain('康乐智助')
  })
  
  it('renders with custom color', () => {
    const wrapper = shallowMount(Logo, {
      localVue,
      propsData: {
        bag: '#ff0000'
      }
    })
    
    const span = wrapper.find('div span')
    expect(span.attributes('style')).toContain('color: rgb(255, 0, 0)')
  })
  
  it('renders el-image component correctly', () => {
    const wrapper = shallowMount(Logo, {
      localVue,
      stubs: {
        'el-image': true
      }
    })
    
    expect(wrapper.findAll('el-image-stub').length).toBe(1)
  })
  
  it('applies correct styles to logo', () => {
    const wrapper = shallowMount(Logo, {
      localVue
    })
    
    const logoElement = wrapper.find('.logo')
    expect(logoElement.classes()).toContain('logo')
  })
}) 